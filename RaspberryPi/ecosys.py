import tkinter as tk
from PIL import Image, ImageTk
import imutils
import time
import cv2
import requests
import subprocess
from requests.exceptions import Timeout
from imutils.video import VideoStream
from pyzbar import pyzbar
import sys
sys.path.append("../..")
from DFRobot_HX711_I2C import *

# Location INFO
# 위치정보
location = "JT_0"

# Ecoarium file Installation path
# 에코아리움 파일 설치 경로
ecosys_path = "/home/pi/Ecoarium"

# Server INFO
server_addr = "http://172.20.10.10:8000"
server_addr_qrcode = server_addr + "/jt/QRCode"
server_addr_img = server_addr + "/jt/determine"
server_pw = "q1w2e3"

# 각종 INFO
userId = "NaN"
nickname = "NaN"
userpoint = -1
cup_weight = 0.0
REFERENCE_WEIGHT = 30   # 빈 컵의 중량 약 14g-15g 사이. 기준 30g 이상이면 이물질 담긴 컵

# I2C connection(LOADCELL)
IIC_MODE         = 0x01            # default use IIC1
IIC_ADDRESS      = 0x64        # default i2c device address
hx711 = DFRobot_HX711_I2C(IIC_MODE ,IIC_ADDRESS)

# DEF: Send QRCODE
def req_qr(qrcode):
    global userId, nickname
    # Function to send data to server
    print(qrcode + '-' + server_addr_qrcode)
    try:
        data = {'QRCode': qrcode, 'key': server_pw }  ################Example data
        response = requests.post(server_addr_qrcode, json=data, timeout=10)  # Set timeout to 10 seconds
        if response.status_code == 200:
            print("[INFO] Data sent successfully to the server.", response.status_code)
            result = response.json()
            print(result)
            return response
        else:
            print("[ERROR] Failed to send data to the server. Status code:", response.status_code)
            return response  # Return response even if status code is not 200
    except Timeout:
        print("[ERROR] Connection to the server timed out. Please try again later.")
    except requests.exceptions.RequestException as e:
        print("[ERROR] An error occurred while sending data to the server:", e)

# DEF: Send IMG
def req_image(img_path):
    global server_addr_img, userId
    try:
        with open(img_path, 'rb') as file:
            files = {'file': file}  # 'file' 키에 파일 객체를 할당
            data = {'key': server_pw, 'userId': userId, 'location': location } 
            response = requests.post(server_addr_img, files=files, data=data)  # 파일 및 기타 데이터를 함께 전송
            if response.status_code == 200:
                print("[INFO] Image uploaded successfully.")
                return response.json()
            else:
                print("[ERROR] Failed to upload image. Status code:", response.status_code)
                return None
    except FileNotFoundError:
        print("[ERROR] Failed to open image file. File not found:", img_path)
        return None
    except Exception as e:
        print("[ERROR] An error occurred while uploading image:", e)
        return None
    

# DEF: 프로그램 실행 시 각 단계를 출력하기 위한 함수
def show_msg_window(message, callback=None):
    # Create the message window
    msg_window = tk.Toplevel()
    msg_window.title("Message")
    
    # Load background image
    msg_bg_image_path = f"{ecosys_path}/tkinter_img/ecosys_background_2.png"
    msg_bg_image = Image.open(msg_bg_image_path)
    msg_bg_image = msg_bg_image.resize((screen_width, screen_height), Image.ANTIALIAS)
    msg_bg_photo = ImageTk.PhotoImage(msg_bg_image)
    

    # Create a Canvas to hold the background image
    canvas = tk.Canvas(msg_window, width=800, height=480)
    canvas.pack(fill="both", expand=True)
    canvas.create_image(0, 0, anchor="nw", image=msg_bg_photo)

    # Create a Label to display the message text
    text_label = tk.Label(msg_window, text=message, font=("Helvetica", 24), wraplength=760, bg="#F7F7F7")
    text_label.place(relx=0.5, rely=0.3, anchor="center")

    # Load button image
    ok_button_image_path = f"{ecosys_path}/tkinter_img/ecosys_btn_ok.png"
    ok_button_image = Image.open(ok_button_image_path)
    ok_button_image = ok_button_image.resize((200, 100), Image.ANTIALIAS)
    ok_button_photo = ImageTk.PhotoImage(ok_button_image)

    if callback:
        # If a callback function is provided, execute it when the close button is clicked
        ok_button = tk.Button(msg_window, image=ok_button_photo, command=lambda: on_window_close(msg_window, callback), width=200, height=100)
    else:
        ok_button = tk.Button(msg_window, image=ok_button_photo, command=msg_window.destroy, width=200, height=100)
    
    if callback != -1:
        ok_button.place(relx=0.5, rely=0.7, anchor="center")  # Adjusted y-position for the button

    # Make sure the window is above the main window
    msg_window.transient()

    # Focus on the message window
    msg_window.grab_set()
    msg_window.focus()

    # Make the window full screen
    msg_window.attributes("-fullscreen", True)

    # Start the event loop for the message window
    msg_window.mainloop()

# DEF: WINDOW CLOSE
def on_window_close(window, callback):
    window.destroy()
    if callback:
        callback()

# DEF: LED를 ON한 다음, 사진을 촬영하고 LED를 다시 OFF
def capture_image():
    #LED ON
    command = f"sudo python3 {ecosys_path}/led.py 1"
    subprocess.run(command, shell=True)

    # 현재 시간 정보 가져오기
    current_time = time.strftime("%Y%m%d-%H%M%S")
    
    # 이미지 파일 이름 구성
    image_name = f"img-{current_time}.jpg"

    # 콘솔 명령어 실행하여 사진 촬영
    command = f"libcamera-still -o {ecosys_path}/img/{image_name}"
    subprocess.run(command, shell=True)

    #LED OFF
    command = f"sudo python3 {ecosys_path}/led.py 0"
    subprocess.run(command, shell=True)

    # 이미지 파일의 전체 경로 반환
    return f"{ecosys_path}/img/{image_name}"

# DEF: QR코드 데이터 읽기
def read_qr():
    # Function to handle QR code login
    print("[INFO] Trying to login...")
    # Initialize video stream and allow camera sensor to warm up
    print("[INFO] Starting video stream...")
    vs = VideoStream(src=0).start()  # Use USB webcam
    time.sleep(1.0)
    
    start_time = time.time()  # Get the start time
    
    # Loop over frames from the video stream
    while True:
        # Grab the frame from the video stream and resize it
        frame = vs.read()
        frame = imutils.resize(frame, width=400)
        
        # Find and decode barcodes in the frame
        barcodes = pyzbar.decode(frame)

        # Loop over detected barcodes
        for barcode in barcodes:
            # Decode barcode data and type
            barcodeData = barcode.data.decode("utf-8")
            
            # Check if barcodeData is not empty
            if barcodeData.strip():
                # Print barcode data to console
                print("[INFO] Barcode Data:", barcodeData)
                # Clean up
                print("[INFO] Cleaning up...")
                cv2.destroyAllWindows()
                vs.stop()
                # Check if the string starts and ends with "
                if barcodeData.startswith('"') and barcodeData.endswith('"'):
                    # If so, remove the first and last characters
                    barcodeData = barcodeData[1:-1]
                return barcodeData
            
        # Show the output frame
        cv2.imshow("Barcode Scanner", frame)
        key = cv2.waitKey(1) & 0xFF
        
        # Check if 10 seconds have passed
        if time.time() - start_time > 20:
            # Clean up
            print("[INFO] Cleaning up...")
            cv2.destroyAllWindows()
            vs.stop()
            return 5  # 반환값이 5일 경우, QR 인식시간 초과 에러

# DEF: LOGIN 기능 구현
def login():
    global nickname, userId, userpoint, login_button
    barcodeData = read_qr()
    if barcodeData == 5:
        show_msg_window("[ERROR CODE 5] 20초 안에 QR코드를 입력하세요")
        return 5
    response = req_qr(barcodeData)
    # 정상적으로 응답이 오면 response에는 userdata가 담김
    if response:  # Check if response is not None
        if response.text == '1':
            show_msg_window("[ERROR CODE 1] 승인되지 않은 유저입니다.")
            return 1
        elif response.text == '2':
            show_msg_window("[ERROR CODE 2] 잘못된 QR 코드입니다.")
            return 2
        elif response.text == '3':
            show_msg_window("[ERROR CODE 3] 로그인 타임아웃. 새로 변경된 QR 코드를 입력하세요.")
            return 3
        else:
            # 로그인 성공
            try:
                user = response.json()
                userId = user['id']
                nickname = user['nickname']
                userpoint = user['points']
                show_msg_window(f"안녕하세요, {nickname}님! 현재 스탬프는 {userpoint}개 입니다.", place_cup_open)
            except ValueError:
                print("[ERROR] Invalid JSON format received from the server.")
                return 4

# DEF: 문 열림
def door_open():
    time.sleep(0.5)
    command = f"sudo {ecosys_path}/ecosys_door o"
    subprocess.run(command, shell=True)

# DEF: 문 닫힘
def door_close():
    time.sleep(0.5)
    command = f"sudo {ecosys_path}/ecosys_door c"
    subprocess.run(command, shell=True)

# DEF: After Login, Open the door
def place_cup_open():
    global cup_weight
    cup_weight = 0.0 #무게 0으로 초기화
    startHX711() #무게센서 start
    door_open() #문열림
    show_msg_window("[INFO] 표시된 위치에 플라스틱 컵을 놓은 뒤 확인을 눌러주세요.", place_cup_close)
    pass

# DEF: After placing the cup in machine, Close the door and capture img
def place_cup_close():
    door_close()
    show_msg_window("[INFO] JT에서 수거가 가능한 컵인지 판별을 진행합니다!", check_cup)
    pass

# DEF: 컵 수거를 위해 문을 오픈
def bring_cup_open():
    global cup_weight
    door_open()
    while True:
        # 컵을 회수하지 않으면 문이 닫히지 않음
        cup_weight = hx711.read_weight(10)
        print('weight is %.1f g' % cup_weight)
        time.sleep(0.2)
        if abs(0 - cup_weight) < 3:  # 컵을 회수한 경우 반복문 종료
            time.sleep(2.0) # 2초 정도 컵을 수거하는 시간을 부여
            break
    door_close()
    pass

# DEF: 로드셀 setup
def startHX711():
    global hx711
    hx711.begin()
    print("weight start\r\n")
    # Manually set the calibration values
    hx711.set_calibration(2210.0)
    # Peel (tare) the scale
    hx711.peel()

# DEF: 컵이 수거 가능한 컵인지 판별하는 함수
def check_cup():
    global userpoint, cup_weight, hx711
    while True:
        # Get the weight of the object
        cup_weight = hx711.read_weight(10)
        print('weight is %.1f g' % cup_weight)
        time.sleep(0.2)
        
        if abs(hx711.read_weight(10) - cup_weight) < 3:  # 컵의 무게 변동이 없을 경우 무게 확정
            break

    if cup_weight > REFERENCE_WEIGHT:  # 컵 무게가 기준값 이상일 경우
        print("[INFO] over weight: %f g" % cup_weight)
        show_msg_window("[INFO] 수거 불가능한 컵입니다!\n컵을 회수하면 문이 자동으로 닫힙니다.\n무게: %.1f g" % cup_weight, bring_cup_open)

    img_path = capture_image()
    response = req_image(img_path)
    if response:
        score = float(response.strip())
        if score > 0.5: # 모델 판정 결과 더러운 컵일 경우!
            show_msg_window("[INFO] 모델 판정 결과 수거가 불가능한 플라스틱 컵입니다!\n컵을 회수하면 문이 자동으로 닫힙니다.", bring_cup_open)
            print("[INFO] Image Uploaded. RESPONSE:", response)
        else: # 깨끗한 컵일 경우
            show_msg_window(f"[INFO] 스탬프 저장이 완료되었습니다!\n현재 {userpoint+1}개의 스탬프를 모았어요.")
            print("[INFO] Image Uploaded. RESPONSE:", response)
    else: # 에러처리
        show_msg_window("[ERROR] 이미지 업로드 실패. 관리자 확인 요망")
        print("[ERROR] Failed to upload image.")
    pass

# DEF: 모든 창 닫기 버튼
def close_all_toplevels():
    global root
    # Tkinter에서 열려진 모든 Toplevel 창을 닫는 함수
    for window in root.winfo_children():
        if isinstance(window, tk.Toplevel):
            window.destroy()

# DEF: 프로그램 오류 시 새로고침 전용 버튼
def refresh():
    global root
    # 현재 윈도우를 닫고 프로그램을 다시 실행
    close_all_toplevels()  # 열려진 모든 Toplevel 창을 닫음
    root.destroy()
    main()

# DEF: 문을 열거나 닫을 수 있도록 관리자 전용 버튼
def show_admin_buttons():
    global open_button, close_button

    # Load open button image
    open_image_path = f"{ecosys_path}/tkinter_img/ecosys_btn_dooropen.png"
    open_image = Image.open(open_image_path)
    open_image = open_image.resize((100, 50), Image.ANTIALIAS)  # 필요에 따라 크기 조정
    open_photo = ImageTk.PhotoImage(open_image)

    # Create and place OPEN button with image
    open_button = tk.Button(root, image=open_photo, command=door_open, width=100, height=50)
    open_button.image = open_photo  # 참조 유지
    canvas.create_window(screen_width * 0.9, screen_height * 0.6, window=open_button)

    # Load close button image
    close_image_path = f"{ecosys_path}/tkinter_img/ecosys_btn_doorclose.png"
    close_image = Image.open(close_image_path)
    close_image = close_image.resize((100, 50), Image.ANTIALIAS)  # 필요에 따라 크기 조정
    close_photo = ImageTk.PhotoImage(close_image)

    # Create and place CLOSE button with image
    close_button = tk.Button(root, image=close_photo, command=door_close, width=100, height=50)
    close_button.image = close_photo  # 참조 유지
    canvas.create_window(screen_width * 0.9, screen_height * 0.75, window=close_button)

# DEF: 에코아리움 멤버 소개
def aboutEcoarium():
    # Create a new window
    ecoarium_window = tk.Toplevel()
    ecoarium_window.title("About Ecoarium")
    
    # Load Ecoarium image
    ecoarium_image_path = f"{ecosys_path}/tkinter_img/ecosys_ecoarium.png"
    ecoarium_image = Image.open(ecoarium_image_path)
    ecoarium_image = ecoarium_image.resize((screen_width, screen_height), Image.ANTIALIAS)
    ecoarium_photo = ImageTk.PhotoImage(ecoarium_image)
    
    # Display Ecoarium image on a label
    ecoarium_label = tk.Label(ecoarium_window, image=ecoarium_photo)
    ecoarium_label.pack(fill="both", expand=True)
    
    # Function to close the window when clicked
    def close_window(event):
        ecoarium_window.destroy()
    
    # Bind the close function to any mouse click event on the window
    ecoarium_window.bind("<Button-1>", close_window)
    
    # Make the window full screen
    ecoarium_window.attributes("-fullscreen", True)
    
    # Make sure the window is above the main window
    ecoarium_window.transient()
    
    # Focus on the Ecoarium window
    ecoarium_window.grab_set()
    ecoarium_window.focus()

    # Start the event loop for the Ecoarium window
    ecoarium_window.mainloop()

# DEF: 메인함수
def main():
    global root, login_button, canvas, screen_height, screen_width

    # Create main window
    root = tk.Tk()
    root.title("Ecoarium")

    # Set window size and position
    root.attributes('-fullscreen', True)
    window_width = 800
    window_height = 480
    screen_width = root.winfo_screenwidth()
    screen_height = root.winfo_screenheight()
    x_coordinate = int((screen_width / 2) - (window_width / 2))
    y_coordinate = int((screen_height / 2) - (window_height / 2))
    root.geometry("%dx%d+%d+%d" % (window_width, window_height, x_coordinate, y_coordinate))

    # Load background image
    bg_image_path = f"{ecosys_path}/tkinter_img/ecosys_background_1.png"
    bg_image = Image.open(bg_image_path)
    bg_image = bg_image.resize((screen_width, screen_height), Image.ANTIALIAS)
    bg_photo = ImageTk.PhotoImage(bg_image)

    # Create canvas and set background image
    canvas = tk.Canvas(root, width=screen_width, height=screen_height)
    canvas.pack(fill="both", expand=True)
    canvas.create_image(0, 0, anchor="nw", image=bg_photo)

    # Load login button image
    login_image_path = f"{ecosys_path}/tkinter_img/ecosys_btn_qrlogin.png"
    login_image = Image.open(login_image_path)
    login_image = login_image.resize((200, 100), Image.ANTIALIAS)  # 필요에 따라 크기 조정
    login_photo = ImageTk.PhotoImage(login_image)

    # QR Login Button with image
    login_button = tk.Button(root, image=login_photo, command=login, width=200, height=100)
    login_button.image = login_photo  # 참조 유지
    canvas.create_window(screen_width // 2, screen_height // 2, window=login_button)

    # Load Ecoarium button image
    ecoarium_image_path = f"{ecosys_path}/tkinter_img/ecosys_btn_ecoarium.png"
    ecoarium_image = Image.open(ecoarium_image_path)
    ecoarium_image = ecoarium_image.resize((100, 50), Image.ANTIALIAS)  # 필요에 따라 크기 조정
    ecoarium_photo = ImageTk.PhotoImage(ecoarium_image)

    # Ecoarium Button with image
    ecoarium_button = tk.Button(root, image=ecoarium_photo, command=aboutEcoarium, width=100, height=50)
    ecoarium_button.image = ecoarium_photo  # 참조 유지
    canvas.create_window(screen_width * 0.9, screen_height * 0.15, window=ecoarium_button)

    # Load quit button image
    quit_image_path = f"{ecosys_path}/tkinter_img/ecosys_btn_quit.png"
    quit_image = Image.open(quit_image_path)
    quit_image = quit_image.resize((100, 50), Image.ANTIALIAS)  # 필요에 따라 크기 조정
    quit_photo = ImageTk.PhotoImage(quit_image)

    # Quit Button with image
    quit_button = tk.Button(root, image=quit_photo, command=root.destroy, width=100, height=50)
    quit_button.image = quit_photo  # 참조 유지
    canvas.create_window(screen_width * 0.9, screen_height * 0.3, window=quit_button)

    # Load refresh button image
    refresh_image_path = f"{ecosys_path}/tkinter_img/ecosys_btn_refresh.png"
    refresh_image = Image.open(refresh_image_path)
    refresh_image = refresh_image.resize((100, 50), Image.ANTIALIAS)  # 필요에 따라 크기 조정
    refresh_photo = ImageTk.PhotoImage(refresh_image)

    # Refresh Button with image
    refresh_button = tk.Button(root, image=refresh_photo, command=refresh, width=100, height=50)
    refresh_button.image = refresh_photo  # 참조 유지
    canvas.create_window(screen_width * 0.9, screen_height * 0.45, window=refresh_button)

    # Load admin button image
    admin_image_path = f"{ecosys_path}/tkinter_img/ecosys_btn_admin.png"
    admin_image = Image.open(admin_image_path)
    admin_image = admin_image.resize((100, 50), Image.ANTIALIAS)  # 필요에 따라 크기 조정
    admin_photo = ImageTk.PhotoImage(admin_image)

    # Admin Button with image
    admin_button = tk.Button(root, image=admin_photo, command=show_admin_buttons, width=100, height=50)
    admin_button.image = admin_photo  # 참조 유지
    canvas.create_window(screen_width * 0.9, screen_height * 0.6, window=admin_button)

    # Start event loop
    root.mainloop()

if __name__ == "__main__":
    main()