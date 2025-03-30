# Author: James Wilhelmi
# Date: 16.02.2025
# Description: Project EAGLE Task 2 main.py

import threading
import cv2
import time
from rwy_detect import frame_rwy_detect # Imports a function from a different module
from analyze_pos import analyze_pos

class RwyDetectSystem:
    def __init__(self): # Calls a new object of the class, acts as a constructor
        #instance variables
        self.frame = None
        self.processed_frame = None
        self.lock = threading.Lock()
        self.exit_flag = False 
        self.capture = cv2.VideoCapture(0)  # Access camera (0 is the default camera)
      
        
        if not self.capture.isOpened():
            raise RuntimeError("ERROR: camera feed can not be accessed")
        # Proof access to camera

    def camera_feed(self): # Thread 1 
        print("press 'q' to cancel the livestream.")       
        while not self.exit_flag:
            ret, new_frame = self.capture.read()
            if not ret:
                print("ERROR: frame is unreadable.")
                break
            with self.lock:
            # Streamlines the program and prevents corruption by ensuring that, only one thread can change a variable at a time 
                self.frame = new_frame.copy()
            time.sleep(0.01)
       
        self.capture.release()
        cv2.destroyAllWindows() # Deallocates camera resource and closes the feed window
   
    def frame_processing(self): 
        # Thread 2 takes frames from Thread 1 and adds wireframes and detected markers to the frame, also prints data onto the terminal
        while not self.exit_flag:
            with self.lock:
                if self.frame is None: 
                    time.sleep(0.01) 
                    continue 
                    # Jumps to start of while loop, prevents NullPointerException
                local_frame = self.frame.copy()
            global center_vectors,aircraft_vectors,rwy_detected
            center_vectors, aircraft_vector, new_processed_frame, rwy_detected = frame_rwy_detect(local_frame)  
            # Calls function frame_rwy_detect and returns four values
            print("Marker position (GCS):", center_vectors)
            print("Aircraft Position (GCS):", aircraft_vector)
            print("Runway detected:", rwy_detected)
            
            with self.lock:
                self.processed_frame = new_processed_frame 

    def frame_display(self): 
        #Thread 3 takes processed frames from Thread 2 and displays them
        while not self.exit_flag:
            with self.lock:
                if self.processed_frame is None:
                    time.sleep(0.01)
                    continue
                display_frame = self.processed_frame.copy()
            
            cv2.imshow('Live Camera Feed', display_frame)
            if cv2.waitKey(1) & 0xFF == ord('q'):
                self.exit_flag = True
                break
        
        cv2.destroyAllWindows()

    def cam_event_loop(self):
        thread1 = threading.Thread(target=self.camera_feed, daemon=True)
        thread2 = threading.Thread(target=self.frame_processing, daemon=True)
        thread3 = threading.Thread(target=self.frame_display, daemon=True)
        # Daemon threads automatically close, when the rest of the program is closed
        thread1.start()
        time.sleep(1)  # Pause to wait for camera feed to start
        thread2.start()
        thread3.start()

    def run(self):
        cam_event_loop()
        thread4 = threading.Thread(target=analyze_pos(center_vectors,aircraft_vector),daemon=True)
 
        try:
            while not self.exit_flag:
                time.sleep(1)
        except KeyboardInterrupt: 
            print("\nExiting program.")
            self.exit_flag = True

if __name__ == "__main__":
    system = RwyDetectSystem()
    system.run()
