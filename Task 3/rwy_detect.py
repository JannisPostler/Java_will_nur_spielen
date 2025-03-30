# Author: Fabian Grill, Jannis Postler
# Date: 15.02.2025
# Description: Project EAGLE Task 2 rwy_detect.py

import numpy as np
import cv2
from cv2 import aruco

# ACS: aircraft coordinate system
# GCS: global coordinate system
GCSOriginInACS = np.array([0, 0, 0])
transformationMatrix = np.matrix([[0, 0, 0], [0, 0, 0], [0, 0, 0]])

# Calibrate your camera and put here your matrix_coefficients and distortion_coefficients
matrix_coefficients = np.matrix([[616.42227817, 0., 327.25358591], [0., 615.92859511, 272.3264952 ], [0., 0., 1.],])
distortion_coefficients = np.matrix([[-3.23209078e-01, 2.58489414e+00, 1.06612675e-02, 4.71462830e-03, -7.17406662e+00]])

# Calculate the axes for the GCS from marker 0
def calculate_axes_from_marker0(rvec_marker0):
    rotation_matrix, _ = cv2.Rodrigues(rvec_marker0) #Turns the rotation vector from marker 0 into a roation matrix
    
    x_axis = rotation_matrix[:, 0] # Takes the first column of the rotation matrix as the new x-axis
    y_axis = rotation_matrix[:, 1] # Takes the second column of the rotation matrix as the new y-axis
    z_axis = -rotation_matrix[:, 2] # Takes the third column of the rotation matrix as the new z-axis
    return x_axis, y_axis, z_axis

# Sets the transformation
def setTransformation(globalMarkerPosInACS, x1, x2, x3):
    #set the origin and the matrix to calculate the coordinate transformation
    #x1, x2, x3 are the direction vectors of the GCS
    #all coordinates are given in ACS coordinates
    global GCSOriginInACS, transformationMatrix
    GCSOriginInACS = globalMarkerPosInACS
    coordinateMatrix = np.matrix([x1, x2, x3])
    transformationMatrix = np.linalg.inv(coordinateMatrix).transpose()

#Calculates the global coordinates of the markers and the aircraft from the aircraft coordinates
def coordinatesInGCS(coordinatesInACS):
    #gets ACS coordinates and returns the corresponding GCS coordinates
    return (coordinatesInACS - GCSOriginInACS) * transformationMatrix

#Detect the four ArUco marker and returns their position in the global coordinate system and the postion of the aircraft
def frame_rwy_detect(frame):
        marker_size = 0.101  # Define the marker size of your printed markers [m]
        marker0_rvec, marker0_tvec = None, None # Stores later the rotation and translation vector of marker 0
        marker_centers = {} # Dictionary to store the detected marker centers
        basis_vectors = None # Stores later the basis vectors of the GCS
        rwy_detected = 0 # Variable to set determine if all four ArUco markers are detected
        
        gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY) # Converts the frame to a grayscale
        aruco_dict = aruco.getPredefinedDictionary(aruco.DICT_4X4_50) # Load the ArUco dictionary
        parameters = aruco.DetectorParameters() # Set up the detector parameters

        corners, ids, _ = aruco.detectMarkers(gray, aruco_dict, parameters=parameters) # Detect ArUco markers in the grayscale image

        if ids is not None: # Check if any markers were detected
            gcs_coordinates_list = [] # List to store the runway corners in the GCS
            
            # Sort the detected Markers IDs with their corners
            sorted_indices = np.argsort(ids.flatten()) 
            ids = ids.flatten()[sorted_indices]
            corners = [corners[i] for i in sorted_indices]
            
            # Check if all four markers were detected
            if (len(ids) == 4): rwy_detected = 1
            else: return None, None, frame, rwy_detected

            for i in range(len(ids)): # Iterate over each detected marker
                # Calculate the postion and rotation of the marker
                rvec, tvec, _ = aruco.estimatePoseSingleMarkers(
                    corners[i], marker_size, matrix_coefficients, distortion_coefficients
                )
                
                marker_id = int(ids[i]) # Get the current marker id
                center = np.array(tvec).flatten() # Calculate 3D postion of the center of the marker in ACS
                marker_centers[marker_id] = center # Store the marker center
                
                if marker_id == 0: # Check if the marker id is 0
                    marker0_rvec, marker0_tvec = rvec[0], tvec[0]
                    basis_vectors = calculate_axes_from_marker0(marker0_rvec) # Calculate the global coordinate system axes based on marker 0
                    setTransformation(marker0_tvec, *basis_vectors) # Sset the transformation matrix using the position and basis vectors from marker 0
                    aircraft_vector = coordinatesInGCS(np.array([0,0,0])) # Compute the aircraft's position in the GCS
                    aircraft_vector[0, 2] = -aircraft_vector[0, 2] # Change the z-coordinate sign
                    cv2.drawFrameAxes(frame, matrix_coefficients, distortion_coefficients, marker0_rvec, marker0_tvec, 0.05) # Used to visualize the axes of the GCS

                aruco.drawDetectedMarkers(frame, corners) # Draw the detected marker on the frame

            if marker0_rvec is not None and marker0_tvec is not None: # Check if translation and rotation vector of marker 0 is calculated
                for marker_id, center in marker_centers.items(): 
                    # Compute GCS coordinates for all markers
                    gcs_coordinates = coordinatesInGCS(center) 
                    gcs_coordinates[0, 2] = -gcs_coordinates[0, 2] # Change the z-coordinate sign
                    gcs_coordinates_list.append(gcs_coordinates.tolist()) # Append to the list of GCS coordinates
                return gcs_coordinates_list, aircraft_vector, frame, rwy_detected # Return the detected marker positions, aircraft position, processed frame, and detection variable
        
        # If no markers were detected, return None values and the original frame
        else: return None, None, frame, rwy_detected
