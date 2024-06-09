import RPi.GPIO as GPIO
import sys

# GPIO 핀 설정
LED_PIN = 26

# GPIO 모드 설정 (BCM 모드)
GPIO.setmode(GPIO.BCM)
GPIO.setup(LED_PIN, GPIO.OUT)

if len(sys.argv) != 2:
    print("Usage: python led_control.py [0|1]")
    sys.exit(1)

try:
    # 명령줄 인자 가져오기
    command = sys.argv[1]

    if command == "0":
        GPIO.output(LED_PIN, GPIO.LOW)  # LED 끄기
        print("LED is OFF")
    elif command == "1":
        GPIO.output(LED_PIN, GPIO.HIGH)  # LED 켜기
        print("LED is ON")
    else:
        print("Invalid argument. Use 0 to turn off and 1 to turn on the LED.")
        sys.exit(1)

except Exception as e:
    print(f"An error occurred: {e}")