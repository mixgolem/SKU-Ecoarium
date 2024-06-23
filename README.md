# Ecoarium - Smart Recycling System
Smart Recycling System은 카페에서 흔히 사용되는 일회용 플라스틱 컵의 재활용률을 높이기 위한 시스템 설계 프로젝트입니다.<br>
일회용 컵 수거 장치, 머신러닝 분류기, AP서버, DB서버, 모바일 APP, 웹, 네트워크 및 보안을 포함합니다.

# Member
<p align="center">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/53ffa411-66ad-48b7-9094-9158f0eb2827" width="800">
</p>

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

# 목차
- [1. Smart Recycling System Infra Structure](#1-Smart-Recycling-System-Infra-Structure)
  - [1.1. DB서버](#11-DB서버)
    - [1.1.1. 클라우드 컴퓨팅 서비스 기반 DB서버 구축](#111-클라우드-컴퓨팅-서비스-기반-DB서버-구축)
    - [1.1.2. DBMS서비스 설치 및 실행](#112-DBMS서비스-설치-및-실행)
    - [1.1.3. ecoarium 스키마 생성](#113-ecoarium-스키마-생성)
    - [1.1.4. 주의사항](#114-주의사항)
  - [1.2. Security](#12-Security)
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
- [4. Ecoarium AP Server, WEB Server](#4-ecoarium-ap-server-web-server)
  - [4.1. 요약](#41-요약)
  - [4.2. 실행 방법](#42-실행-방법)
  - [4.3. 기능 설명](#43-기능-설명)
    - [4.3.1. QR 코드 유저 인증 API](#431-qr-코드-유저-인증-api)
    - [4.3.2. 플라스틱 컵 청결도 검사 API](#432-플라스틱-컵-청결도-검사-api)
    - [4.3.3. 회원가입 / 로그인 기능](#433-회원가입--로그인-기능)
    - [4.3.4. 메인 페이지](#434-메인-페이지)
    - [4.3.5. QR 코드 생성 기능](#435-qr-코드-생성-기능)
    - [4.3.6. 스토어](#436-스토어)
    - [4.3.7. 마이페이지](#437-마이페이지)
    - [4.3.8. 보관함](#438-보관함)
    - [4.3.9. 프로필 수정](#439-프로필-수정)
    - [4.3.10. 관리자페이지](#4310-관리자페이지)
    - [4.3.11. 비밀번호 찾기](#4311-비밀번호-찾기)
- [5. Ecoarium Application](#5-ecoarium-application)
  - [5.1. 소개](#51-소개)
  - [5.2. 주요 기능](#52-주요-기능)
  - [5.3. 주요 구성](#53-주요-구성)
    - [5.3.0. 기능 블록도](#530-기능-블록도)
    - [5.3.1. 로그인 페이지](#531-로그인-페이지)
    - [5.3.2. 회원가입 페이지](#532-회원가입-페이지)
    - [5.3.3. 메인 페이지](#533-메인-페이지)
    - [5.3.4. 홈 페이지](#534-홈-페이지)
    - [5.3.5. 스토어 페이지](#535-스토어-페이지)
    - [5.3.6. QR 페이지](#536-QR-페이지)
    - [5.3.7. 보관함 페이지](#537-보관함-페이지)
    - [5.3.8. 마이 페이지](#538-마이-페이지)
    - [5.3.9. 회원 관리 페이지](#539-회원-관리-페이지)
    - [5.3.10. 기타](#5310-기타)
  - [5.4. 설치 및 실행](#54-설치-및-실행)
    - [5.4.1. APK 설치 및 실행 방법](#541-APK-설치-및-실행-방법)
    - [5.4.2. 가상머신 사용 방법 (Android 환경이 아닌 경우)](#542-가상머신-사용-방법-android-환경이-아닌-경우)
    - [5.4.3. APK 생성 및 실행 방법 (APK 설치 과정에서 오류가 발생한 경우)](#543-apk-생성-및-실행-방법-apk-설치-과정에서-오류가-발생한-경우)
    - [5.4.4. Android SDK 설정이 안된 경우](#544-android-sdk-설정이-안된-경우)
      - [5.4.4.1. ANDROID_HOME 환경 변수 설정](#5441-android_home-환경-변수-설정)
      - [5.4.4.2. local.properties 설정](#5442-localproperties-설정)
  - [5.5. 개발 환경](#55-개발-환경)

# 1. Smart Recycling System Infra Structure
Ecoarium은 단순 장치 구동을 구현한 것이 아닌 Smart Recycling System을 설계하였습니다. <br>
이 저장소는 해당 시스템의 Infra Structure와 그를 구성하는 DB서버와 DBMS, 그에 대한 System 보안을 기술합니다. <br>
- Ecoarium이 고안한 Smart Recycling System 구조는 안정성, 보안성, 성능 향상 등의 효율성 증대를 위해 AP–DB 이원화 서버를 구축하였습니다. <br>
- DB서버와 JT, Web/Mobile Application은 각각 Client로서 AP서버의 Web서버에 접속합니다. <br>
- JT, Web/Mobile Application은 DB로의 직접적인 통신이 불가하며, AP(Web) Server를 거쳐 통신합니다.
<br>
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/56341387/51d32200-bd0b-4ac3-b3a5-a4711640b3ba" alt="Smart Recycling System 구성도" style="width: 70%;"><br>

## 1.1. DB서버
Ecoarium은 단순 장치 구동을 구현한 것이 아닌 System을 설계했습니다. <br>

### 1.1.1. 클라우드 컴퓨팅 서비스 기반 DB서버 구축
System 확장성을 위해 클라우드 컴퓨팅 서비스인 Linux CentOS7 기반의 AWS EC2 instance를 생성합니다.
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/56341387/7ecbe3b9-9f80-41e2-b9c8-bd28d072f411" alt="Linux CentOS7기반 AWS EC2 instance 생성" style="width: 70%;"><br>

### 1.1.2. DBMS서비스 설치 및 실행
안정적이고 빠른 성능을 제공하면서도 오픈 소스 기반으로 비용 효율적인 RDBMS인 MySQL 8.0을 사용합니다.  <br>

- MySQL 8.0 설치 명령어 <br>
~~~
yum install –y https://dev.mysql.com/get/mysql80-community-release-el7-3.noarch.rpm
yum install -y mysql-community-server
~~~
- MySQL 8.0 시작 및 부팅시 자동시작 설정 명령어 <br>
~~~
systemctl start mysqld
systemctl enable mysqld
~~~
- MySQL 8.0 진입 명령 <br>
~~~
mysql –u root -p
~~~
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/56341387/b674475c-4130-475d-a25d-ae20cf860fcf" alt="MySQL 설치 및 실행" style="width: 70%;"><br>

### 1.1.3. ecoarium 스키마 생성
- 쿼리문 <br>
~~~
CREATE DATABASE IF NOT EXISTS ecoarium;
USE ecoarium;
~~~
### 1.1.4. 주의사항
테이블링과 실제 DB서버에 적재되는 데이터 CRUD는 Ecoarium AP(Web)서버 Back-End에서 Node.js 라이브러리인 Sequelize에 의해 실행됩니다. <br>
따라서, AP서버와의 연동을 위해 root계정이 아닌 원격접속용 계정(new_username)을 생성합니다. <br>

- 원격접속용 계정 생성 및 권한부여 쿼리문 <br>
~~~
CREATE USER 'new_username'@'localhost' IDENTIFIED BY 'new_password';
GRANT ALL PRIVILEGES ON *.* TO 'new_username'@'localhost' WITH GRANT OPTION;
~~~
이후, DB서버와 AP(Web)서버의 시퀄라이즈 연동을 위해 node.js Web서버 config.json파일의 적색 부분을 다음과 같이 수정합니다. <br>
이 때, 주의할 점으로 host의 ip(dns)주소 인스턴스의 내부ip주소인 public ip(dns)로 접속합니다.<br>
<br>
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/56341387/27e34ee6-301f-47ee-bca8-3d160c00a65e" alt="MySQL 설치 및 실행" style="width: 70%;"><br>

## 1.2. Security
Ecoarium은 기밀성과 무결성이 보장되어야 하는 중요한 사용자 데이터를 안전하게 관리합니다. <br> 
중요한 사용자 정보가 저장되는 DB서버와 DBMS(MySQL)에 보안체크리스트(정책)를 작성하고, 취약점 점검 스크립트를 실행하여 보이지 않는 위협 또한 제거합니다. <br>
이 저장소 내의 Ecoarium DB 서버 보안점검리스트.pdf 파일은 해당 내용을 기술합니다.

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

# 4. Ecoarium AP Server, WEB Server
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
</p>
Ecoarium의 AP/WEB Server 파트는 node.js로 개발하여 사용자의 서비스 이용을 위한 웹 서버와 모바일 어플리케이션 서비스를 위한 API, 그리고 JT기기와 통신하는 API 및 머신러닝 모델을 포함하고 있습니다.<br><br>
<p align="center">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/0490472c-6a0a-4084-bd10-e61d7546c4cd">
</p>

## 4.1. 요약
사용자는 웹을 통해 계정을 생성하여 로그인하고 QR코드를 생성해 JT 기기에 인식합니다. QR코드 값을 인식한 JT 기기는 서버에 HTTP 통신을 통해 인증을 요청하고, 서버는 DB에서 유저정보를 조회하여 응답합니다.
그 후 사용자는 JT기기에 컵을 투입하고, JT기기는 컵을 촬영하여 서버에 측정을 요청합니다.
요청받은 서버는 머신러닝 모델을 실행해 사진을 측정하여 결과값을 응답하고, 깨끗하다고 판단한 경우 인증한 사용자의 DB 데이터의 스탬프 값을 증가시키고 로그를 생성합니다.
사용자는 스탬프를 수집하여 스토어를 통해 원하는 상품을 구매할 수 있습니다.<br>
<p align="center">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/5ecc1c3a-8eae-48f0-98b6-7b13f3984d6b">
</p>

## 4.2. 실행 방법
#### 필요 스택<br>
<img src="https://img.shields.io/badge/Node.js-5FA04E?style=flat-square&logo=Node.js&logoColor=white"> v18.16.0 
<img src="https://img.shields.io/badge/Python-3776AB?style=flat-square&logo=Python&logoColor=white"> v3.12 
<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white"> v8.0
1. VS Code로 Ecoarium 디렉토리 열기<br>
2. config/config.json 수정

```
"username": "Database Username",
"password": "Database Password",
"database": "ecoarium",
"host": "Database Address",
"dialect": "mysql"
``` 
3. Ecoarium 디렉토리에 .env 파일 생성

```
COOKIE_SECRET=ecoariumsecret
JT_SECRET=q1w2e3
EMAIL_USER=비밀번호 찾기 시 이메일을 전송할 Gmail
EMAIL_PASS=앱 비밀번호
```
4. 터미널 명령어 실행<br>
- 패키지 설치

```
npm i
```
- DB Schema 생성

```
sequelize db:create
```
5. app.js 실행
<br><br>

## 4.3. 기능 설명

### 4.3.1. QR 코드 유저 인증 API
<p align="center">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/7f4ac284-602d-429c-976d-842f44c1b90e"><br>
  routes/jt.js
</p>
JT기기에서 유저가 인식한 QR코드 값과 API 사용 보안을 위한 key값을 포함해 요청받습니다. key값이 환경 변수로 저장된 값과 일치하지 않는 경우 응답을 하지 않습니다.
DB에서 QR코드 값과 일치하는 유저를 조회합니다. 인증에 실패할 경우 유저 식별 실패, QR코드 값 불일치, QR코드 유효시간 초과에 따라 정해진 오류 코드를 응답합니다. 인증에 성공할 경우 유저 데이터를 응답합니다.

### 4.3.2. 플라스틱 컵 청결도 검사 API
<p align="center">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/1feed00b-ecd6-4dd6-81a7-e7bccf5aba26"><br>
  routes/jt.js
</p>
JT기기에서 유저가 투입한 컵을 촬영한 사진과 유저 테이블Id, JT기기 식별코드, key값을 포함해 요청받습니다. key값이 환경 변수로 저장된 값과 일치하지 않는 경우 응답을 하지 않습니다.
머신러닝 모델을 실행하여 컵의 청결도를 측정하고, 측정에 실패한 경우 사진 전송 오류, 모델 실행 오류, 모델 결과값 오류에 따라 정해진 오류 코드를 응답합니다.
측정에 성공한 경우 결과값을 응답하고, 깨끗하다고 판단한 경우 인증한 사용자의 DB 데이터의 스탬프 값을 증가시키고, 스탬프 획득 로그를 생성합니다.

### 4.3.3. 회원가입 / 로그인 기능
<p align="center">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/30cf9f52-bb62-4b3c-9c85-929bb3d8fd45">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/7323eb77-3e50-4b86-9280-1264cbd80afe">
</p>
passport 모듈을 사용해 로그인 기능을 구현하였습니다. 회원가입 성공 시 DB 유저 테이블에는 아이디와 비밀번호, 이메일, 닉네임이 저장됩니다.

### 4.3.4. 메인 페이지
<p align="center">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/e80a41e2-0e84-41a1-80bf-a9fd01da48a5">
</p>


메인 페이지는 내가 모은 스탬프를 시각적으로 보여줍니다. 적립 QR 버튼을 누를 시 QR코드가 생성되며, 상단 레이아웃에 스토어, 마이페이지로 이동하는 버튼이 있습니다.

### 4.3.5. QR 코드 생성 기능
<p align="center">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/07ce2ad7-9ad5-41b5-ae42-51ed38f651ed">
</p>
적립 QR 생성 시 서버는 유저, 시간 정보를 바탕으로 코드를 생성하여 DB에 저장 및 클라이언트에 전송을 합니다.
1분의 유효시간을 가지게 되며, 이 때 DB에 저장된 값은 사용자가 JT에 QR을 인식하고 JT가 서버에 API를 요청한 QR코드 값과 대조하게 됩니다.

### 4.3.6. 스토어
<p align="center">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/afa16a52-f082-46fa-87f1-0370bde84f7f">
</p>
사용자는 스탬프를 모아 스토어에 있는 상품을 구매할 수 있습니다.
구매를 요청하고 충분한 스탬프를 보유하여 구매에 성공 시 서버는 DB 유저 데이터의 스탬프 값을 감소시키고, 스탬프 사용 로그와 상품 데이터를 생성합니다.
상품 데이터는 상품Id, 사용 여부, 소유자Id를 포함하고, 고유한 식별 코드를 부여받습니다.

### 4.3.7. 마이페이지
<p align="center">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/02e6b7a3-4c06-40fd-947f-2c2a47b543cc">
</p>
마이페이지에서는 내 프로필, 스탬프 내역, 보관함을 확인할 수 있습니다.
스탬프 내역은 전체 / 적립 / 사용 탭으로 구분해 확인할 수 있습니다.
각 내역들은 시간과 획득처 / 사용 내용 등의 정보를 포함합니다.
이 내역 목록은 무한 스크롤로 구현해 스크롤을 맨 아래로 내릴 때마다 기록을 10개씩 로딩합니다.

### 4.3.8. 보관함
<p align="center">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/24ee580b-16d7-49bb-99eb-b2345236248d">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/5d7fe23a-fa17-449d-98b9-fb894c9cc4e5">
</p>
스토어에서 구매한 상품을 보관함에서 확인할 수 있습니다. 사용가능 탭의 상품을 클릭 시 바코드를 확인할 수 있습니다.

### 4.3.9. 프로필 수정
<p align="center">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/8c57bc35-b391-4318-b429-b8c794a186cb"><br>
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/d7c0d325-9bc4-4932-90c9-3f83b25633e3">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/0f93a84b-6c48-476e-ac8f-7798e63a3674">
</p>
프로필 수정 페이지에서 닉네임, 이메일, 비밀번호를 수정할 수 있고, 회원탈퇴를 할 수 있습니다.

### 4.3.10. 관리자페이지
<p align="center">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/ea270de3-2d93-4541-bae7-04d33574c428">
</p>
관리자로 지정된 계정으로 관리자 페이지로 이동해 관리자 기능을 이용할 수 있습니다.
보관함의 상품의 코드를 입력해 상품을 사용완료 상태로 변경할 수 있고, 스토어의 상품을 추가할 수 있습니다.


### 4.3.11. 비밀번호 찾기
<p align="center">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/fc6040e3-684f-46c7-bef1-9fdb1d01db21">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/61be95fa-5936-40b6-b466-c9a0e98f681c">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/9ac14df5-61ed-428c-89ed-5427fc8090e6">
</p>
로그인 단계에서 비밀번호 찾기로 이동할 수 있습니다. 사용자가 아이디를 입력 시 서버는 계정에 저장 된 이메일로 인증 코드를 보냅니다.
인증 코드는 16자리 랜덤 문자열로 생성되고, DB에 시간 정보와 함께 저장됩니다.
인증 코드는 1분의 유효시간을 가지고, 사용자는 이메일을 확인해 인증 코드를 입력하고, 비밀번호를 재설정 할 수 있습니다.
<br><br><br>

---
<br><br><br>

# 5. Ecoarium Application
Ecoarium Application은 Smart Recycling System 프로젝트의 일환으로 개발되었습니다.<br>
이 저장소는 해당 프로젝트의 소스 코드와 apk 파일 등을 포함하고 있습니다.
<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=flat-square&logo=kotlin&logoColor=white">
  <img src="https://img.shields.io/badge/Android-34A853?style=flat-square&logo=android&logoColor=white">
  <img src="https://img.shields.io/badge/Intellijidea-000000?style=flat-square&logo=intellijidea&logoColor=white">
  <img src="https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=github&logoColor=white">
</p>

## 5.1. 소개
Ecoarium App은 사용자가 JT를 통해 플라스틱 폐기물을 반납하는 과정에서 쉬운 접근과 편리한 사용감을 제공하기 위해 제작되었습니다.<br>
손쉽게 플라스틱 폐기물을 재활용하는 과정을 통해 환경 보호에 기여하고 이를 통해 적립된 포인트로 다양한 상품을 교환해보세요!

## 5.2. 주요 기능
- QR코드 인증 : 유저의 고유한 QR코드를 인식하여 인증 과정을 보다 편리하게 이용할 수 있습니다.
- 스탬프 적립 : 재활용 가능한 플라스틱 폐기물을 수거했을 경우 일정량의 포인트를 적립할 수 있습니다.
- 다양한 상품 교환 : 적립된 포인트를 사용해서 스토어에 등록된 다양한 상품을 구매할 수 있습니다.
- 환경 정보 : 현재 진행중인 환경 관련 캠페인, 이벤트 등을 확인할 수 있고 폐기물 수거 현황 등을 모니터링할 수 있습니다.

## 5.3. 주요 구성
### 5.3.0. 기능 블록도
<p align="center">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/153111259/f94d3ea3-e877-4cae-b073-a98769a674f0" alt="Ecoarium 네트워크 구성도" style="width: 70%;"><br>
  <em>Ecoarium 네트워크 구성도</em>
</p>


<p align="center">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/153111259/3dad1f6a-fd9f-4386-959a-4eae6ec4615c" alt="Mobile App 기능 블록도" style="width: 70%;"><br>
  <em>Mobile App 기능 블록도</em>
</p>
<br>

### 5.3.1. 로그인 페이지
<p align="left">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/153111259/cbad82a6-0a5b-44d4-8bb6-770c688a0601" alt="로그인" style="width: 40%;">
</p>

- 로그인 기능 구현
- 비밀번호 표시/숨김 기능 구현
- 자동 로그인 기능 구현
- 쿠키를 사용한 세션 유지 기능 구현
> #### 관련 코드
> - [LoginActivity](Mobile/app/src/main/java/com/example/ecoariumapp/activities/LoginActivity.kt)
> - [sendLoginRequest](Mobile/app/src/main/java/com/example/ecoariumapp/sendRequests/sendLoginRequest.kt)
<br>

### 5.3.2. 회원가입 페이지
<p align="left">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/153111259/b4ff81a0-3108-4cc2-ae37-b10a973c513d" alt="회원가입" style="width: 40%;">
</p>

- 회원가입 기능 구현
- 비밀번호 표시/숨김 기능 구현
> #### 관련 코드
> - [RegisterActivity](Mobile/app/src/main/java/com/example/ecoariumapp/activities/RegisterActivity.kt)
> - [sendRegisterRequest](Mobile/app/src/main/java/com/example/ecoariumapp/sendRequests/sendRegisterRequest.kt)
<br>

### 5.3.3. 메인 페이지
- Fragment의 생명 주기 관리 기능 구현
- 백그라운드 로그인 유지 기능 구현
- 네비게이션 바 기능 구현
> #### 관련 코드
> - [MainActivity](Mobile/app/src/main/java/com/example/ecoariumapp/activities/MainActivity.kt)
<br>

### 5.3.4. 홈 페이지
<p align="left">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/153111259/f750e42f-a2f5-4e60-b72b-adb222c5f930" alt="홈" style="width: 40%;">
</p>

- View Pager 형식의 캠페인/이벤트 뷰어 구현
- 미니 프로필 뷰어 구현
- JT 수거 현황 뷰어 구현
> #### 관련 코드
> - [HomeFragment](Mobile/app/src/main/java/com/example/ecoariumapp/fragments/HomeFragment.kt)
> - [sendHomeRequest](Mobile/app/src/main/java/com/example/ecoariumapp/sendRequests/sendHomeRequest.kt)
> - [sendStacksRequest](Mobile/app/src/main/java/com/example/ecoariumapp/sendRequests/sendStacksRequest.kt)
<br>

### 5.3.5. 스토어 페이지
<p align="left">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/153111259/8a8e5452-f35f-4520-8d5c-9ac5fa1c6908" alt="스토어" style="width: 40%;">
</p>

- Recycler View 형식의 상품 뷰어 구현
- 상품 교환 기능 구현
> #### 관련 코드
> - [StoreFragment](Mobile/app/src/main/java/com/example/ecoariumapp/fragments/StoreFragment.kt)
> - [sendStoreRequest](Mobile/app/src/main/java/com/example/ecoariumapp/sendRequests/sendStoreRequest.kt)
> - [sendExchangeRequest](Mobile/app/src/main/java/com/example/ecoariumapp/sendRequests/sendExchangeRequest.kt)
<br>

### 5.3.6. QR 페이지
<p align="left">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/153111259/608d242c-9fa9-4430-a588-271aeccbe7de" alt="QR" style="width: 40%;">
</p>

- QR코드 생성 및 이미지 뷰 구현
- QR코드 유효 시간 알림 타이머 구현
> #### 관련 코드
> - [QRcodeFragment](Mobile/app/src/main/java/com/example/ecoariumapp/fragments/QRcodeFragment.kt)
> - [sendQRcodeRequest](Mobile/app/src/main/java/com/example/ecoariumapp/sendRequests/sendQRcodeRequest.kt)
<br>

### 5.3.7. 보관함 페이지
<p align="left">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/153111259/98a6d1e3-a03b-4b1b-83cb-89d3746ad4ba" alt="보관함" style="width: 40%;">
</p>

- Recycler View 형식의 상품 뷰어 구현
- 보관함 페이지 정렬 버튼 ( 사용 가능, 사용 완료 ) 구현
- 상품 고유 바코드 이미지 뷰 구현
> #### 관련 코드
> - [InventoryFragment](Mobile/app/src/main/java/com/example/ecoariumapp/fragments/InventoryFragment.kt)
> - [sendInventoryRequest](Mobile/app/src/main/java/com/example/ecoariumapp/sendRequests/sendInventoryRequest.kt)
<br>

### 5.3.8. 마이 페이지
<p align="left">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/153111259/748c366b-bb32-43f2-bffd-c2b3a5b32449" alt="마이페이지" style="width: 40%;">
</p>

- 프로필 뷰어 구현
- 로그아웃 기능 구현
- Recycler View 형식의 스탬프 내역 뷰어 구현
- 스탬프 내역 정렬 버튼 ( 전체, 적립, 사용 ) 구현
> #### 관련 코드
> - [MypageFragment](Mobile/app/src/main/java/com/example/ecoariumapp/fragments/MypageFragment.kt)
> - [sendMypageRequest](Mobile/app/src/main/java/com/example/ecoariumapp/sendRequests/sendMypageRequest.kt)
> - [sendLogoutRequest](Mobile/app/src/main/java/com/example/ecoariumapp/sendRequests/sendLogoutRequest.kt)
> - [sendStampLogsRequest](Mobile/app/src/main/java/com/example/ecoariumapp/sendRequests/sendStampLogsRequest.kt)
<br>

### 5.3.9. 회원 관리 페이지
<p align="left">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/153111259/78ffd892-7407-4982-ac57-3b20f47efd8a" alt="회원 정보 수정" style="width: 40%;">
</p>

- 프로필 아이콘 수정 기능 구현
- 닉네임 수정 기능 구현
- 비밀번호 수정 기능 구현
- 비밀번호 표시/숨김 기능 구현
- 로그아웃 기능 구현
- 회원 탈퇴 기능 구현
> #### 관련 코드
> - [ProfileFragment](Mobile/app/src/main/java/com/example/ecoariumapp/fragments/ProfileFragment.kt)
> - [sendProfileRequest](Mobile/app/src/main/java/com/example/ecoariumapp/sendRequests/sendProfileRequest.kt)
> - [sendModifyRequest](Mobile/app/src/main/java/com/example/ecoariumapp/sendRequests/sendModifyRequest.kt)
> - [sendChangePasswordRequest](Mobile/app/src/main/java/com/example/ecoariumapp/sendRequests/sendChangePasswordRequest.kt)
> - [sendLogoutRequest](Mobile/app/src/main/java/com/example/ecoariumapp/sendRequests/sendLogoutRequest.kt)
> - [sendDeleteRequest](Mobile/app/src/main/java/com/example/ecoariumapp/sendRequests/sendDeleteRequest.kt)
<br>

### 5.3.10. 기타
- Recycler View, View Pager 등의 데이터 바인딩을 위한 Adapter 구현
- SharedPreference를 관리하기 위한 SharedPrefManager 구현
- 지역 및 형식에 맞게 시간을 표시하기 위한 TimeConverter 구현
- 서버 IP를 설정하기 위한 Ipconfig 구현
- 배달의 민족 폰트 "주아체" 사용
> #### 관련 코드
> - [InventoryRecyclerViewAdapter](Mobile/app/src/main/java/com/example/ecoariumapp/adapters/InventoryRecyclerViewAdapter.kt)
> - [StampRecyclerViewAdapter](Mobile/app/src/main/java/com/example/ecoariumapp/adapters/StampRecyclerViewAdapter.kt)
> - [StoreRecyclerViewAdapter](Mobile/app/src/main/java/com/example/ecoariumapp/adapters/StoreRecyclerViewAdapter.kt)
> - [ViewPagerAdapter](Mobile/app/src/main/java/com/example/ecoariumapp/adapters/ViewPagerAdapter.kt)
> - [TimeConverter](Mobile/app/src/main/java/com/example/ecoariumapp/TimeConverter.kt)
> - [SharedPrefManager](Mobile/app/src/main/java/com/example/ecoariumapp/sharedPreferences/SharedPrefManager.kt)
> - [Ipconfig](Mobile/app/src/main/java/com/example/ecoariumapp/Ipconfig.kt)

## 5.4. 설치 및 실행
> ⚠️ **경고:** 본 Application은 Android 환경의 Mobile Device에 설치 가능합니다.

### 5.4.1. APK 설치 및 실행 방법
- Repository에 첨부 된 APK 파일을 다운로드 받습니다. (Mobile - app - release - Ecoarium.apk)
- APK 파일을 Android 기기에 전송합니다.
- 기기에서 파일 관리자를 열고, APK 파일을 찾습니다.
- APK 파일을 클릭하여 설치를 시작합니다. (설치 전, 기기의 설정에서 '알 수 없는 앱'에 대한 설치를 허용해야 할 수 있습니다.)
- 설치가 완료되면 앱을 실행하여 사용을 시작합니다.
  
### 5.4.2. 가상머신 사용 방법 (Android 환경이 아닌 경우)
- GitHub 저장소에서 프로젝트를 클론합니다.
- Android Studio에서 'Run' > 'Run 'app''을 선택합니다.
- Android Studio를 열고, 'File' > 'Open'을 선택하여 클론한 프로젝트를 엽니다.
- 연결된 Android 기기를 선택하거나, 새 Android Virtual Device를 생성합니다.
- 'OK'를 클릭하여 앱을 실행합니다.

### 5.4.3. APK 생성 및 실행 방법 (APK 설치 과정에서 오류가 발생한 경우)
- GitHub 저장소에서 프로젝트를 클론합니다.
- Android Studio를 열고, 'File' > 'Open'을 선택하여 클론한 프로젝트를 엽니다.
- 'Build' > 'Build Bundle(s) / APK(s)' > 'Build APK(s)'를 선택하여 APK를 빌드합니다.
- 빌드가 완료되면, 'locate' 버튼을 클릭하여 APK 파일을 찾습니다.
- APK 파일을 Android 기기에 전송하고, 파일을 클릭하여 설치합니다. (설치 전, 기기의 설정에서 '알 수 없는 앱'에 대한 설치를 허용해야 할 수 있습니다.)
- 설치가 완료되면 앱을 실행하여 사용을 시작합니다.

### 5.4.4. Android SDK 설정이 안된 경우
> Android SDK의 위치가 제대로 설정되지 않은 오류 등이 발생한 경우 아래 두가지 방법 중 하나를 시도 해보시기 바랍니다.
#### 5.4.4.1. ANDROID_HOME 환경 변수 설정
- 시작 메뉴를 열고 "환경 변수 편집"을 검색하여 "시스템 환경 변수 편집"을 선택합니다.
- 시스템 속성 창에서 "환경 변수..." 버튼을 클릭합니다.
- 환경 변수 창에서 "새로 만들기..."를 클릭합니다.
- 변수 이름에는 ANDROID_HOME을 입력하고, 변수 값에는 SDK 경로를 입력합니다.
- 시스템 변수 목록에서 'Path' 변수를 찾아 선택하고 "편집..."을 클릭합니다.
- 새 항목을 추가하여 '%ANDROID_HOME%\tools'와 '%ANDROID_HOME%\platform-tools' 경로를 입력합니다.

#### 5.4.4.2. local.properties 설정
- 프로젝트 루트 디렉토리로 이동하여 'local.properties' 파일을 찾습니다.
- local.properties 파일을 열거나 새로 만듭니다.
- 다음 줄을 추가하여 SDK경로를 지정합니다
> ⚠️ **경고:** 아래 경로는 Windows 파일 경로로써 두개의 백 슬래쉬를 사용합니다, 운영 체제에 맞는 경로로 바꾸어 적용하시길 바랍니다.
  ```properties
  sdk.dir=C:\\Users\\<YourUsername>\\AppData\\Local\\Android\\Sdk
  ```
## 5.5. 개발 환경
- IDE : IntelliJ IDEA 2023.2.2 (Community Edition)
- Language : Kotlin 1.9.0
- Android Virtual Device
  - Model : Pixel 4 XL (6.3inch flexible OLED, 19:9, 3040x1440, 537ppi)
  - OS : Android 11.0
- Android Tablet
  - Model : Galaxy Tab S8+ (12.4inch Super AMOLED, 16:10, 2800x1752, 266ppi)
  - OS : Android 13.0
