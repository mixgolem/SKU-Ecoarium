# Ecoarium - Smart Recycling System
에코아리움 설명~~

# Member
<p align="center">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/53ffa411-66ad-48b7-9094-9158f0eb2827" width="800">
</p>

# 목차
- [1. Ecoarium JT](#1-Ecoarium-JT)
  - [1.1. JT - 플라스틱 컵 수거 장치](#11-jt---플라스틱-컵-수거-장치)
    - [1.1.1. JT 초기 화면](#111-jt-초기-화면)
    - [1.1.2. QR 로그인](#112-qr-로그인)
    - [1.1.3. 로그인 성공 후 투입구 열림, 컵 투입](#113-로그인-성공-후-투입구-열림-컵-투입)
    - [1.1.4. 투입구 닫힌 후 컵 사진 촬영](#114-투입구-닫힌-후-컵-사진-촬영)
    - [1.1.5. 컵 수거 완료](#115-컵-수거-완료)
    - [1.1.6. 컵 수거 불가](#116-컵-수거-불가)
- [2. Plastic Cup Classifier](#2-Plastic-Cup-Classifier)
- [3. Ecoarium AP Server, WEB Server](#3)
- [4. Ecoarium Application](#4)

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

# 4. Ecoarium Application
Ecoarium Application은 Smart Recycling System 프로젝트의 일환으로 개발되었습니다.<br>
이 저장소는 해당 프로젝트의 소스 코드와 apk 파일 등을 포함하고 있습니다.

## 4.1 소개
Ecoarium App은 사용자가 JT를 통해 플라스틱 폐기물을 반납하는 과정에서 쉬운 접근과 편리한 사용감을 제공하기 위해 제작되었습니다.<br>
손쉽게 플라스틱 폐기물을 재활용하는 과정을 통해 환경 보호에 기여하고 이를 통해 적립된 포인트로 다양한 상품을 교환해보세요!

## 4.2 주요 기능
- QR코드 인증 : 유저의 고유한 QR코드를 인식하여 인증 과정을 보다 편리하게 이용할 수 있습니다.
- 스탬프 적립 : 재활용 가능한 플라스틱 폐기물을 수거했을 경우 일정량의 포인트를 적립할 수 있습니다.
- 다양한 상품 교환 : 적립된 포인트를 사용해서 스토어에 등록된 다양한 상품을 구매할 수 있습니다.
- 환경 정보 : 현재 진행중인 환경 관련 캠페인, 이벤트 등을 확인할 수 있고 폐기물 수거 현황 등을 모니터링할 수 있습니다.

## 4.3 주요 구성
### 4.3.0. 기능 블록도
<p align="center">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/153111259/f94d3ea3-e877-4cae-b073-a98769a674f0" alt="Ecoarium 네트워크 구성도" style="width: 70%;"><br>
  <em>Ecoarium 네트워크 구성도</em>
</p>


<p align="center">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/153111259/3dad1f6a-fd9f-4386-959a-4eae6ec4615c" alt="Mobile App 기능 블록도" style="width: 70%;"><br>
  <em>Mobile App 기능 블록도</em>
</p>
<br>

### 4.3.1. 로그인 페이지
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

### 4.3.2. 회원가입 페이지
<p align="left">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/153111259/b4ff81a0-3108-4cc2-ae37-b10a973c513d" alt="회원가입" style="width: 40%;">
</p>

- 회원가입 기능 구현
- 비밀번호 표시/숨김 기능 구현
> #### 관련 코드
> - [RegisterActivity](Mobile/app/src/main/java/com/example/ecoariumapp/activities/RegisterActivity.kt)
> - [sendRegisterRequest](Mobile/app/src/main/java/com/example/ecoariumapp/sendRequests/sendRegisterRequest.kt)
<br>

### 4.3.3. 메인 페이지
- Fragment의 생명 주기 관리 기능 구현
- 백그라운드 로그인 유지 기능 구현
- 네비게이션 바 기능 구현
> #### 관련 코드
> - [MainActivity](Mobile/app/src/main/java/com/example/ecoariumapp/activities/MainActivity.kt)
<br>

### 4.3.4. 홈 페이지
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

### 4.3.5. 스토어 페이지
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

### 4.3.6. QR 페이지
<p align="left">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/153111259/608d242c-9fa9-4430-a588-271aeccbe7de" alt="QR" style="width: 40%;">
</p>

- QR코드 생성 및 이미지 뷰 구현
- QR코드 유효 시간 알림 타이머 구현
> #### 관련 코드
> - [QRcodeFragment](Mobile/app/src/main/java/com/example/ecoariumapp/fragments/QRcodeFragment.kt)
> - [sendQRcodeRequest](Mobile/app/src/main/java/com/example/ecoariumapp/sendRequests/sendQRcodeRequest.kt)
<br>

### 4.3.7. 보관함 페이지
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

### 4.3.8. 마이 페이지
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

### 4.3.9. 회원 관리 페이지
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

### 4.3.10. 기타
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

## 4.4 설치 및 실행
> ⚠️ **경고:** 본 Application은 Android 환경의 Mobile Device에 설치 가능합니다.

### 4.4.1 APK 설치 및 실행 방법
- Repository에 첨부 된 APK 파일을 다운로드 받습니다. (Mobile - app - release - Ecoarium.apk)
- APK 파일을 Android 기기에 전송합니다.
- 기기에서 파일 관리자를 열고, APK 파일을 찾습니다.
- APK 파일을 클릭하여 설치를 시작합니다. (설치 전, 기기의 설정에서 '알 수 없는 앱'에 대한 설치를 허용해야 할 수 있습니다.)
- 설치가 완료되면 앱을 실행하여 사용을 시작합니다.
  
### 4.4.2 가상머신 사용 방법 (Android 환경이 아닌 경우)
- GitHub 저장소에서 프로젝트를 클론합니다.
- Android Studio에서 'Run' > 'Run 'app''을 선택합니다.
- Android Studio를 열고, 'File' > 'Open'을 선택하여 클론한 프로젝트를 엽니다.
- 연결된 Android 기기를 선택하거나, 새 Android Virtual Device를 생성합니다.
- 'OK'를 클릭하여 앱을 실행합니다.

### 4.4.3 APK 생성 및 실행 방법 (APK 설치 과정에서 오류가 발생한 경우)
- GitHub 저장소에서 프로젝트를 클론합니다.
- Android Studio를 열고, 'File' > 'Open'을 선택하여 클론한 프로젝트를 엽니다.
- 'Build' > 'Build Bundle(s) / APK(s)' > 'Build APK(s)'를 선택하여 APK를 빌드합니다.
- 빌드가 완료되면, 'locate' 버튼을 클릭하여 APK 파일을 찾습니다.
- APK 파일을 Android 기기에 전송하고, 파일을 클릭하여 설치합니다. (설치 전, 기기의 설정에서 '알 수 없는 앱'에 대한 설치를 허용해야 할 수 있습니다.)
- 설치가 완료되면 앱을 실행하여 사용을 시작합니다.

### 4.4.4 Android SDK 설정이 안된 경우
> Android SDK의 위치가 제대로 설정되지 않은 오류 등이 발생한 경우 아래 두가지 방법 중 하나를 시도 해보시기 바랍니다.
#### 4.4.4.1. ANDROID_HOME 환경 변수 설정
- 시작 메뉴를 열고 "환경 변수 편집"을 검색하여 "시스템 환경 변수 편집"을 선택합니다.
- 시스템 속성 창에서 "환경 변수..." 버튼을 클릭합니다.
- 환경 변수 창에서 "새로 만들기..."를 클릭합니다.
- 변수 이름에는 ANDROID_HOME을 입력하고, 변수 값에는 SDK 경로를 입력합니다.
- 시스템 변수 목록에서 'Path' 변수를 찾아 선택하고 "편집..."을 클릭합니다.
- 새 항목을 추가하여 '%ANDROID_HOME%\tools'와 '%ANDROID_HOME%\platform-tools' 경로를 입력합니다.

#### 4.4.4.2. local.properties 설정
- 프로젝트 루트 디렉토리로 이동하여 'local.properties' 파일을 찾습니다.
- local.properties 파일을 열거나 새로 만듭니다.
- 다음 줄을 추가하여 SDK경로를 지정합니다
> ⚠️ **경고:** 아래 경로는 Windows 파일 경로로써 두개의 백 슬래쉬를 사용합니다, 운영 체제에 맞는 경로로 바꾸어 적용하시길 바랍니다.
  ```properties
  sdk.dir=C:\\Users\\<YourUsername>\\AppData\\Local\\Android\\Sdk
  ```
