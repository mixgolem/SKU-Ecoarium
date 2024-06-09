# Ecoarium JT & Plastic Cup Classifier
<p align="center">
  <img src="https://img.shields.io/badge/tensorflow-FF6F00?style=for-the-badge&logo=tensorflow&logoColor=white">
  <img src="https://img.shields.io/badge/Python-3776AB?style=for-the-badge&logo=Python&logoColor=white">
  <img src="https://img.shields.io/badge/Clang-A8B9CC?style=for-the-badge&logo=c&logoColor=white">
</p>

---

## JT;플라스틱 컵 수거 장치
<p align="center">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/b47ee6d1-2bfd-4b1f-85a5-75f385b62bc2">
</p>

    * 일회용 플라스틱 컵 수거 시스템을 위한 장치
    * 스테인리스(STS304 1.2T POLISHING) 재질, W450*D150*H300mm
    * RaspberryPi 4B bullseye
    * 7inch touch Display, Servo motor*2, Camera module, Webcam, Loadcell, LED
    * JT S/W는 Python tkinter Library로 제작
    * QR코드 인식 OpenCV2, imutils
    * HTTP 통신을 사용해 AP서버, Android APP과 통신

### 동작 화면

#### 1) JT 초기 화면

<img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/8659afbe-70d6-40ab-a3b3-36425d0a8a4f">


#### 2) QR 로그인

![qrlogin](https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/cd7156d6-74b1-41a0-9aba-26478e092d73)


#### 3) 로그인 성공 후 투입구 열림, 컵 투입

![cupin](https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/dd184eb6-0a6f-40b7-aca9-f3a667ac02fb)


#### 4) 투입구 닫힌 후 컵 사진 촬영

![captureimg](https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/f983947a-3eb1-4e3c-b6af-30be5ce98a6b)


#### 5) 컵 수거 완료

여기에 이미지 추가


#### 6) 컵 수거 불가

여기에 이미지 추가


---

## JT;플라스틱 컵 분류기
<p align="center">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/f5393994-810a-4ce4-b153-8fc599dc0bf0">
</p>

    * 일회용 플라스틱 컵 재활용 수거 여부 판별
    * Python, NumPy, Tensorflow, CNN, 이진 분류
    * 0.5 초과일 경우 수거 불능, 0.5 이하일 경우 수거 가능!
