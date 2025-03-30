import math
import numpy as np
import csv
import os

def analyze_pos(runwayCoordinates,aircraftVector):
    tdwnDistance=.15
    appAngle=3

    if(rwy_detected):
        width=runwayCoordinates[1][0] #width_vector of the runway
        length=runwayCoordinates[2][1] #length_vector of the runway
        dHeight=runwayCoordinates[2][2] #height difference beween v1 and v4
        tdwnPos=np.array([width*.5,
                         length*tdwnDistance,
                         dHeight*tdwnDistance]
                         )
        runwayAngle=math.atan(dHeight/length) #for uneven runways
        approachdir=np.array([0, -1, math.tan(math.radians(appAngle-runwayAngle))])

        #equation of a line: x=tdwnPos+r*approachdir
        #approachdir[1]=-1, so aircraftVector[1]=tdwnPos[1]-r
        #solve for r:

        r=-aircraftVector[1]+tdwnPos[1]
        t=tdwnPos+r*approachdir
        hOffset=round(aircraftVector[0]-t[0],1)  #horizontal Offset
        vOffset=round(aircraftVector[2]-t[2],1)  #vertical Offset
        print(123)
        return [hOffset,vOffset]
    return None, None
