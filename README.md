# Ecoarium Application
Ecoarium Application은 Smart Recycling System 프로젝트의 일환으로 개발되었습니다.<br>
이 저장소는 해당 프로젝트의 소스 코드와 apk 파일 등을 포함하고 있습니다.

## 소개
Ecoarium App은 사용자가 JT를 통해 플라스틱 폐기물을 반납하는 과정에서 쉬운 접근과 편리한 사용감을 제공하기 위해 제작되었습니다.<br>
손쉽게 플라스틱 폐기물을 재활용하는 과정을 통해 환경 보호에 기여하고 이를 통해 적립된 포인트로 다양한 상품을 교환해보세요!

## 주요 기능
- QR코드 인증 : 유저의 고유한 QR코드를 인식하여 인증 과정을 보다 편리하게 이용할 수 있습니다.
- 스탬프 적립 : 재활용 가능한 플라스틱 폐기물을 수거했을 경우 일정량의 포인트를 적립할 수 있습니다.
- 다양한 상품 교환 : 적립된 포인트를 사용해서 스토어에 등록된 다양한 상품을 구매할 수 있습니다.
- 환경 정보 : 현재 진행중인 환경 관련 캠페인, 이벤트 등을 확인할 수 있고 폐기물 수거 현황 등을 모니터링할 수 있습니다.

## 제작
### 1. 로그인 페이지
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

### 2. 회원가입 페이지
<p align="left">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/153111259/b4ff81a0-3108-4cc2-ae37-b10a973c513d" alt="회원가입" style="width: 40%;">
</p>

- 회원가입 기능 구현
- 비밀번호 표시/숨김 기능 구현
> #### 관련 코드
> - [RegisterActivity](Mobile/app/src/main/java/com/example/ecoariumapp/activities/RegisterActivity.kt)
> - [sendRegisterRequest](Mobile/app/src/main/java/com/example/ecoariumapp/sendRequests/sendRegisterRequest.kt)

### 3. 메인 페이지
- Fragment의 생명 주기 관리 기능 구현
- 백그라운드 로그인 유지 기능 구현
- 네비게이션 바 기능 구현
> #### 관련 코드
> - [MainActivity](Mobile/app/src/main/java/com/example/ecoariumapp/activities/MainActivity.kt)

### 4. 홈 페이지
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

### 5. 스토어 페이지
<p align="left">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/153111259/8a8e5452-f35f-4520-8d5c-9ac5fa1c6908" alt="스토어" style="width: 40%;">
</p>

- Recycler View 형식의 상품 뷰어 구현
- 상품 교환 기능 구현
> #### 관련 코드
> - [StoreFragment](Mobile/app/src/main/java/com/example/ecoariumapp/fragments/StoreFragment.kt)
> - [sendStoreRequest](Mobile/app/src/main/java/com/example/ecoariumapp/sendRequests/sendStoreRequest.kt)
> - [sendExchangeRequest](Mobile/app/src/main/java/com/example/ecoariumapp/sendRequests/sendExchangeRequest.kt)

### 6. QR 페이지
<p align="left">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/153111259/608d242c-9fa9-4430-a588-271aeccbe7de" alt="QR" style="width: 40%;">
</p>

- QR코드 생성 및 이미지 뷰 구현
- QR코드 유효 시간 알림 타이머 구현
> #### 관련 코드
> - [QRcodeFragment](Mobile/app/src/main/java/com/example/ecoariumapp/fragments/QRcodeFragment.kt)
> - [sendQRcodeRequest](Mobile/app/src/main/java/com/example/ecoariumapp/sendRequests/sendQRcodeRequest.kt)

### 7. 보관함 페이지
<p align="left">
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/153111259/98a6d1e3-a03b-4b1b-83cb-89d3746ad4ba" alt="보관함" style="width: 40%;">
</p>

- Recycler View 형식의 상품 뷰어 구현
- 보관함 페이지 정렬 버튼 ( 사용 가능, 사용 완료 ) 구현
- 상품 고유 바코드 이미지 뷰 구현
> #### 관련 코드
> - [InventoryFragment](Mobile/app/src/main/java/com/example/ecoariumapp/fragments/InventoryFragment.kt)
> - [sendInventoryRequest](Mobile/app/src/main/java/com/example/ecoariumapp/sendRequests/sendInventoryRequest.kt)

### 8. 마이 페이지
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

### 9. 회원 관리 페이지
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

### 10. 기타
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

## 설치 및 실행
> ⚠️ **경고:** 본 Application은 Android 환경의 Mobile Device에 설치 가능합니다.

### APK 설치 및 실행 방법
- Repository에 첨부 된 APK 파일을 다운로드 받습니다. (Mobile - app - release - Ecoarium.apk)
- APK 파일을 Android 기기에 전송합니다.
- 기기에서 파일 관리자를 열고, APK 파일을 찾습니다.
- APK 파일을 클릭하여 설치를 시작합니다. (설치 전, 기기의 설정에서 '알 수 없는 앱'에 대한 설치를 허용해야 할 수 있습니다.)
- 설치가 완료되면 앱을 실행하여 사용을 시작합니다.
  
### 가상머신 사용 방법 (Android 환경이 아닌 경우)
- GitHub 저장소에서 프로젝트를 클론합니다.
- Android Studio에서 'Run' > 'Run 'app''을 선택합니다.
- Android Studio를 열고, 'File' > 'Open'을 선택하여 클론한 프로젝트를 엽니다.
- 연결된 Android 기기를 선택하거나, 새 Android Virtual Device를 생성합니다.
- 'OK'를 클릭하여 앱을 실행합니다.

### APK 생성 및 실행 방법 (APK 설치 과정에서 오류가 발생한 경우)
- GitHub 저장소에서 프로젝트를 클론합니다.
- Android Studio를 열고, 'File' > 'Open'을 선택하여 클론한 프로젝트를 엽니다.
- 'Build' > 'Build Bundle(s) / APK(s)' > 'Build APK(s)'를 선택하여 APK를 빌드합니다.
- 빌드가 완료되면, 'locate' 버튼을 클릭하여 APK 파일을 찾습니다.
- APK 파일을 Android 기기에 전송하고, 파일을 클릭하여 설치합니다. (설치 전, 기기의 설정에서 '알 수 없는 앱'에 대한 설치를 허용해야 할 수 있습니다.)
- 설치가 완료되면 앱을 실행하여 사용을 시작합니다.
  
![ecosys_ecoarium](https://github.com/mixgolem/SKU-Ecoarium/assets/130221911/771cbbe8-66df-462c-b2a7-5c5c65a6e05c)
