# Ecoarium - Smart Recycling System
Smart Recycling System은 카페에서 흔히 사용되는 일회용 플라스틱 컵의 재활용률을 높이기 위한 시스템 설계 프로젝트입니다.<br>
일회용 컵 수거 장치, 머신러닝 분류기, AP서버, DB서버, 모바일 APP, 웹, 네트워크 및 보안을 포함합니다.

_현재 README는 kcy브랜치에 관한 내용만 포함됩니다_

## 목차
- [2. Ecoarium JT 플라스틱 컵 수거 장치](#2-Ecoarium-JT-플라스틱-컵-수거-장치)
  - [2.1. 기술스택&개발환경](#21-기술스택개발환경)
  - [2.2. 설계](#22-설계)
  - [2.3. 동작 과정](#23-동작-과정)
    - [2.3.1. JT 초기 화면](#231-jt-초기-화면)
    - [2.3.2. QR 로그인](#232-qr-로그인)
    - [2.3.3. 로그인 성공 후 컵 투입](#233-로그인-성공-후-컵-투입)
    - [2.3.4. 무게 측정, 사진 촬영해서 검사](#234-무게-측정-사진-촬영해서-검사)
    - [2.3.5. 컵 수거 완료](#235-컵-수거-완료)
    - [2.3.6. 컵 수거 불가능](#236-컵-수거-불가능)
- [3. Plastic Cup Classifier](#3-Plastic-Cup-Classifier)
- [Member](#member)

# 2. Ecoarium JT 플라스틱 컵 수거 장치
에코아리움의 플라스틱 컵 수거 장치인 JT로 손쉽게 플라스틱 컵을 재활용 할 수 있습니다. 손쉽게 로그인하고, 컵을 투입한 뒤, 스탬프를 획득하세요!
<br>

## 2.1. 기술스택&개발환경
<p align="center">
  <img src="https://img.shields.io/badge/Tensorflow-FF6F00?style=flat-square&logo=tensorflow&logoColor=white">
  <img src="https://img.shields.io/badge/Python-3776AB?style=flat-square&logo=Python&logoColor=white">
  <img src="https://img.shields.io/badge/C lang-A8B9CC?style=flat-square&logo=c&logoColor=white">
  <img src="https://img.shields.io/badge/vscode-007ACC?style=flat-square&logo=visualstudiocode&logoColor=white">
  <img src="https://img.shields.io/badge/Raspberry%20Pi-A22846?style=flat-square&logo=RaspberryPi&logoColor=white">
  <img src="https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=github&logoColor=white">
</p>

- IDE : Visual Studio Code 1.90
- Python: 3.11.9
- Tensorflow: 2.16.1
- gcc: 6.3.0
- Raspberry Pi 4B Bullseye
<br>

## 2.2. 설계
<img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/b47ee6d1-2bfd-4b1f-85a5-75f385b62bc2" width="800"><br>
<img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/6410845f-b612-4088-aea9-a8d835407e3d" width="800"><br>

- 일회용 플라스틱 컵 수거 시스템을 위한 장치
- 스테인리스(STS304 1.2T POLISHING) 재질, W450*D150*H300mm
- RaspberryPi 4B bullseye
- 7inch touch Display, Servo motor*2, Camera module, Webcam, Loadcell, LED
- JT S/W는 Python tkinter Library로 제작
- QR코드 인식 OpenCV2, imutils
- HTTP 통신을 사용해 AP서버, Android APP과 통신
<br>

## 2.3. 동작 과정
### 2.3.1. JT 초기 화면
- 기기의 첫 화면입니다.<br>
<img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/8659afbe-70d6-40ab-a3b3-36425d0a8a4f" width="600"><br>

### 2.3.2. QR 로그인
- APP에서 여러분의 QR코드를 기기에 인식시켜 주세요!<br>
<img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/c37ef1be-d91e-4f1b-b263-4f9955cb3085" width="600"><br>
<img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/5515f342-600f-4cb1-8b00-2866b2423f7a" width="600"><br>
<img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/85ae953f-8a82-4252-a34d-ce96eff280cb" width="600"><br>

### 2.3.3. 로그인 성공 후 컵 투입
- JT는 재활용이 가능한 깨끗한 컵만을 수거합니다.<br>
<img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/61940e86-b820-46c9-9c41-4768527efe57" width="600"><br>
<img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/203053cd-fdea-4567-bd5e-e7c6cb32ed5a" width="600"><br>

### 2.3.4. 무게 측정, 사진 촬영해서 검사
- 2가지 방식을 사용하여 검사합니다.<br>
<img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/897597a6-3726-4c2a-a0df-4f2a321349e4" width="300">
<img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/3f8e58df-bb6d-4bf6-9cf2-af68b56f921b" width="300">
<img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/481eb6d7-0227-4e25-bff8-e2e589fd3121" width="600"><br>

### 2.3.5. 컵 수거 완료
- 성공적으로 스탬프를 저장했습니다.<br>
<img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/59c86379-ef7b-4c6d-8f28-c1b343ba31cd" width="600"><br>

### 2.3.6. 컵 수거 불가능
- 음료가 묻어있는 컵을 넣어보겠습니다.<br>
<img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/d44996db-998b-4f3c-9d1e-246446942db6" width="600"><br>
- 무게 센서는 통과했지만 모델 판정 결과 수거할 수 없는 컵이기에, 사용자가 직접 수거합니다.<br> 
<img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/9c56563e-4946-4918-8bd6-b34062170f72" width="600"><br>
- 다음은 얼음이 담긴 컵입니다.<br>
<img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/afe5112a-7290-47c6-ae88-7dcec2ab212b" width="600"><br>
- 무게센서에 걸려서 사진 촬영 없이 바로 사용자가 직접 수거합니다.<br>
<img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/06c1398e-bf21-4eb9-a992-5aa317465ef7" width="600"><br>

# 3. Plastic Cup Classifier
<img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/e37e3a87-ad66-4896-ae78-6595e3ede64e" width="800"><br>
<img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/f5393994-810a-4ce4-b153-8fc599dc0bf0" width="800"><br>

- 일회용 플라스틱 컵 재활용 수거 여부 판별
- Python, NumPy, Tensorflow, CNN, 이진 분류
- 0.5 초과일 경우 수거 불능, 0.5 이하일 경우 수거 가능!

# Member
<p align="center">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/53ffa411-66ad-48b7-9094-9158f0eb2827" width="800">
</p>
