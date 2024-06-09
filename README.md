# Ecoarium - Smart Recycling System
에코아리움 설명~~

# Member
<p align="center">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/53ffa411-66ad-48b7-9094-9158f0eb2827" width="800">
</p>

# 목차
[1. Ecoarium JT](#1-Ecoarium-JT)

[2. Plastic Cup Classifier](#2-Plastic-Cup-Classifier)

[3. Ecoarium AP Server, WEB Server](#3)

[4. Ecoarium Application](#4)

# Tech Stack
<p align="center">
  <img src="https://img.shields.io/badge/Node.js-5FA04E?style=flat-square&logo=Node.js&logoColor=white">
  <img src="https://img.shields.io/badge/Express-000000?style=flat-square&logo=express&logoColor=white">
  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=javascript&logoColor=white">
  <img src="https://img.shields.io/badge/HTML5-E34F26?style=flat-square&logo=html5&logoColor=white">
  <img src="https://img.shields.io/badge/EJS-B4CA65?style=flat-square&logo=ejs&logoColor=white">
  <img src="https://img.shields.io/badge/CSS3-1572B6?style=flat-square&logo=css3&logoColor=white">
  <img src="https://img.shields.io/badge/Tensorflow-FF6F00?style=flat-square&logo=tensorflow&logoColor=white">
  <img src="https://img.shields.io/badge/Python-3776AB?style=flat-square&logo=Python&logoColor=white">
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white">
  <img src="https://img.shields.io/badge/C-A8B9CC?style=flat-square&logo=c&logoColor=white">
  <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=flat-square&logo=kotlin&logoColor=white">
  <img src="https://img.shields.io/badge/Android-34A853?style=flat-square&logo=android&logoColor=white">
  <img src="https://img.shields.io/badge/vscode-007ACC?style=flat-square&logo=visualstudiocode&logoColor=white">
  <img src="https://img.shields.io/badge/Intellijidea-000000?style=flat-square&logo=intellijidea&logoColor=white">
  <img src="https://img.shields.io/badge/Raspberry%20Pi-A22846?style=flat-square&logo=RaspberryPi&logoColor=white"/>
  <img src="https://img.shields.io/badge/CentOS-262577?style=flat-square&logo=centos&logoColor=white">
  <img src="https://img.shields.io/badge/Windows-0078D4?style=flat-square&logo=Windows&logoColor=white"/>
  <img src="https://img.shields.io/badge/Linux-FCC624?style=flat-square&logo=Linux&logoColor=black"/>
  <img src="https://img.shields.io/badge/AWS-FF9900?style=flat-square&logo=amazonwebservices&logoColor=white">
  <img src="https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=github&logoColor=white">
</p>

<br><br><br>

# 1. Ecoarium JT
<p align="center">
  <img src="https://img.shields.io/badge/Tensorflow-FF6F00?style=flat-square&logo=tensorflow&logoColor=white">
  <img src="https://img.shields.io/badge/Python-3776AB?style=flat-square&logo=Python&logoColor=white">
  <img src="https://img.shields.io/badge/C lang-A8B9CC?style=flat-square&logo=c&logoColor=white">
  <img src="https://img.shields.io/badge/vscode-007ACC?style=flat-square&logo=visualstudiocode&logoColor=white">
  <img src="https://img.shields.io/badge/Raspberry%20Pi-A22846?style=flat-square&logo=RaspberryPi&logoColor=white">
  <img src="https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=github&logoColor=white">
</p>

    * IDE : Visual Studio Code 1.90
    * Python: 3.11.9
    * Tensorflow: 2.16.1
    * gcc: 6.3.0
    * Raspberry Pi 4B Bullseye

---

## 1.1. JT - 플라스틱 컵 수거 장치
<p align="center">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/b47ee6d1-2bfd-4b1f-85a5-75f385b62bc2">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/6410845f-b612-4088-aea9-a8d835407e3d">
</p>

    * 일회용 플라스틱 컵 수거 시스템을 위한 장치
    * 스테인리스(STS304 1.2T POLISHING) 재질, W450*D150*H300mm
    * RaspberryPi 4B bullseye
    * 7inch touch Display, Servo motor*2, Camera module, Webcam, Loadcell, LED
    * JT S/W는 Python tkinter Library로 제작
    * QR코드 인식 OpenCV2, imutils
    * HTTP 통신을 사용해 AP서버, Android APP과 통신

#### 1.1.1. JT 초기 화면

<img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/8659afbe-70d6-40ab-a3b3-36425d0a8a4f" width="600">

#### 1.1.2. QR 로그인

<img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/cd7156d6-74b1-41a0-9aba-26478e092d73" width="600">

#### 1.1.3. 로그인 성공 후 투입구 열림, 컵 투입

<img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/dd184eb6-0a6f-40b7-aca9-f3a667ac02fb" width="600">

#### 1.1.4. 투입구 닫힌 후 컵 사진 촬영

<img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/f983947a-3eb1-4e3c-b6af-30be5ce98a6b" width="600">

#### 1.1.5. 컵 수거 완료

<img src="" width="600">

#### 1.1.6. 컵 수거 불가

<img src="" width="600">

---

<br><br><br>

# 2. Plastic Cup Classifier
<p align="center">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/e37e3a87-ad66-4896-ae78-6595e3ede64e">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/f5393994-810a-4ce4-b153-8fc599dc0bf0">
</p>

    * 일회용 플라스틱 컵 재활용 수거 여부 판별
    * Python, NumPy, Tensorflow, CNN, 이진 분류
    * 0.5 초과일 경우 수거 불능, 0.5 이하일 경우 수거 가능!

  ---
