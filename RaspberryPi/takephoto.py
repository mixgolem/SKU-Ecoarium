import time
import subprocess

# 현재 시간 정보 가져오기
current_time = time.strftime("%Y%m%d-%H%M%S")

# 이미지 파일 이름 구성
image_name = f"img-{current_time}.jpg"

# 콘솔 명령어 실행하여 사진 촬영
command = f"libcamera-still -o /home/pi/Ecoarium/img/{image_name}"
subprocess.run(command, shell=True)