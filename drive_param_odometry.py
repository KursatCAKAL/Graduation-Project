import rospy
from nav_msgs.msg import Odometry
from geometry_msgs.msg import Pose, Twist, Transform, TransformStamped
from gazebo_msgs.msg import LinkStates
from std_msgs.msg import Header
import numpy as np
import math
import tf2_ros
import present
import binascii
class OdometryNode:
    pub_odom = rospy.Publisher('/vesc/odom', Odometry, queue_size=1)

    def __init__(self):

        self.last_received_pose = Pose()
        self.last_received_twist = Twist()
        self.last_recieved_stamp = None
        self.xtry=0
        self.ytry=0
	self.encryptedX=0
	self.encryptedY=0
		#self.key = '10000000000000000000'#.decode('hex')
		#self.pre_instance=present.Present(self.key)
        rospy.Timer(rospy.Duration(.05), self.timer_callback) # 20hz
        self.tf_pub = tf2_ros.TransformBroadcaster()
        rospy.Subscriber('/gazebo/link_states', LinkStates, self.sub_robot_pose_update)

    def sub_robot_pose_update(self, msg):
        # Find the index of the racecar
        try:
            arrayIndex = msg.name.index('racecar::base_link')
        except ValueError as e:
            # Wait for Gazebo to startup
            pass
        else:
            # Extract our current position information
            self.last_received_pose = msg.pose[arrayIndex]
            self.last_received_twist = msg.twist[arrayIndex]

        self.last_recieved_stamp = rospy.Time.now()

    def timer_callback(self, event):
        if self.last_recieved_stamp is None:
            return

        cmd = Odometry()
        cmd.header.stamp = self.last_recieved_stamp
        cmd.header.frame_id = 'map'
        cmd.child_frame_id = 'odom'
        cmd.pose.pose = self.last_received_pose
        self.xtry=cmd.pose.pose.position.x
        self.ytry=cmd.pose.pose.position.y
        #print str(str("X   ")+str(cmd.pose.pose.position.x))
        #print str(str("Y   ")+str(cmd.pose.pose.position.y))
        #print str(str("Z   ")+str(cmd.pose.pose.position.z))
        cmd.twist.twist = self.last_received_twist
        self.pub_odom.publish(cmd)
	"""
	xParam=float(str(self.last_received_pose)[17:25])
	yParam=str(self.last_received_pose)[33:36]
	#yParam=self.last_received_pose.pose.position.x
	#xParam=float(str(self.last_received_pose)[25])
	print(str(self.last_received_pose))
	#print(str("float")+str(float(str(self.last_received_pose)[17:25])))
	#print("{0:.0f}".format(xParam))
	"""
	xParam=cmd.pose.pose.position.x
	yParam=cmd.pose.pose.position.y
	lastCoordinateX="{0:.0f}".format(xParam)
	lastCoordinateY="{0:.0f}".format(yParam)
		
	key = '10000000000200000000'.decode('hex')
	cipher = present.Present(key)
	plainX=binascii.hexlify(lastCoordinateX).decode('hex')
	plainY=binascii.hexlify(lastCoordinateY).decode('hex')
	encryptedX = cipher.encrypt(plainX)
	encryptedY = cipher.encrypt(plainY)
        self.encryptedX=encryptedX.encode('hex')
        self.encryptedY=encryptedY.encode('hex')
	#print str(str("X :  ")+str(encryptedX.encode('hex')))
	#print str(str("Y :  ")+str(encryptedY.encode('hex')))

	#print(str(encrypted.encode('hex')))

	#print(self.last_received_twist)
        tf = TransformStamped(
            header=Header(
                frame_id=cmd.header.frame_id,
                stamp=cmd.header.stamp
            ),
            child_frame_id=cmd.child_frame_id,
            transform=Transform(
                translation=cmd.pose.pose.position,
                rotation=cmd.pose.pose.orientation
            )
        )
        self.tf_pub.sendTransform(tf)

# Start the node
if __name__ == '__main__':
    rospy.init_node("gazebo_odometry_node")
    node = OdometryNode()
    rospy.spin()
