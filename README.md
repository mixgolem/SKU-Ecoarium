# Ecoarium AP Server
Ecoarium 프로젝트는 사용자가 JT기기(수거 기기)를 통해 일회용 플라스틱 컵을 반납하여 스탬프를 적립받는 서비스입니다.
Ecoarium의 AP Server 파트는 node.js로 개발하여 사용자의 서비스 이용을 위한 웹 서버와 모바일 어플리케이션 서비스를 위한 API, 그리고 JT기기와 통신하는 API 및 머신러닝 모델을 포함하고 있습니다.<br><br>
![상세](https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/0490472c-6a0a-4084-bd10-e61d7546c4cd)<br><br>

## 요약
사용자는 웹을 통해 계정을 생성하여 로그인하고 QR코드를 생성해 JT 기기에 인식합니다. QR코드 값을 인식한 JT 기기는 서버에 HTTP 통신을 통해 인증을 요청하고, 서버는 DB에서 유저정보를 조회하여 응답합니다.
그 후 사용자는 JT기기에 컵을 투입하고, JT기기는 컵을 촬영하여 서버에 측정을 요청합니다.
요청받은 서버는 머신러닝 모델을 실행해 사진을 측정하여 결과값을 응답하고, 깨끗하다고 판단한 경우 인증한 사용자의 DB 데이터의 스탬프 값을 증가시키고 로그를 생성합니다.
사용자는 스탬프를 수집하여 스토어를 통해 원하는 상품을 구매할 수 있습니다.<br>
![image](https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/5ecc1c3a-8eae-48f0-98b6-7b13f3984d6b)
<br>

## 실행 방법
ㅁㄴㅇㄻㄴㅇㄹ
<br><br>

## 기능 설명

### QR 코드 유저 인증 API
![image](https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/7f4ac284-602d-429c-976d-842f44c1b90e)<br>
routes/jt.js<br>
JT기기에서 유저가 인식한 QR코드 값과 API 사용 보안을 위한 key값을 포함해 요청받습니다. key값이 환경 변수로 저장된 값과 일치하지 않는 경우 응답을 하지 않습니다.
DB에서 QR코드 값과 일치하는 유저를 조회합니다. 인증에 실패할 경우 유저 식별 실패, QR코드 값 불일치, QR코드 유효시간 초과에 따라 정해진 오류 코드를 응답합니다. 인증에 성공할 경우 유저 데이터를 응답합니다.

### 플라스틱 컵 청결도 검사 API
![image](https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/1feed00b-ecd6-4dd6-81a7-e7bccf5aba26)<br>
routes/jt.js<br>
JT기기에서 유저가 투입한 컵을 촬영한 사진과 유저 테이블Id, JT기기 식별코드, key값을 포함해 요청받습니다. key값이 환경 변수로 저장된 값과 일치하지 않는 경우 응답을 하지 않습니다.
머신러닝 모델을 실행하여 컵의 청결도를 측정하고, 측정에 실패한 경우 사진 전송 오류, 모델 실행 오류, 모델 결과값 오류에 따라 정해진 오류 코드를 응답합니다.
측정에 성공한 경우 결과값을 응답하고, 깨끗하다고 판단한 경우 인증한 사용자의 DB 데이터의 스탬프 값을 증가시키고, 스탬프 획득 로그를 생성합니다.

### 회원가입 / 로그인 기능
![image](https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/30cf9f52-bb62-4b3c-9c85-929bb3d8fd45)
![image](https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/7323eb77-3e50-4b86-9280-1264cbd80afe)<br>
로그인, 회원가입 페이지<br>
passport 모듈을 사용해 로그인 기능을 구현하였습니다. 회원가입 성공 시 DB 유저 테이블에는 아이디와 비밀번호, 이메일, 닉네임이 저장됩니다.

### 메인 페이지
![image](https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/e80a41e2-0e84-41a1-80bf-a9fd01da48a5)<br>

메인 페이지는 내가 모은 스탬프를 시각적으로 보여줍니다. 적립 QR 버튼을 누를 시 QR코드가 생성되며, 상단 레이아웃에 스토어, 마이페이지로 이동하는 버튼이 있습니다.

### QR 코드 생성 기능
![image](https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/07ce2ad7-9ad5-41b5-ae42-51ed38f651ed)<br>
적립 QR 생성 시 서버는 유저, 시간 정보를 바탕으로 코드를 생성하여 DB에 저장 및 클라이언트에 전송을 합니다.
1분의 유효시간을 가지게 되며, 이 때 DB에 저장된 값은 사용자가 JT에 QR을 인식하고 JT가 서버에 API를 요청한 QR코드 값과 대조하게 됩니다.

### 스토어
![image](https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/afa16a52-f082-46fa-87f1-0370bde84f7f)<br>
사용자는 스탬프를 모아 스토어에 있는 상품을 구매할 수 있습니다.
구매를 요청하고 충분한 스탬프를 보유하여 구매에 성공 시 서버는 DB 유저 데이터의 스탬프 값을 감소시키고, 스탬프 사용 로그와 상품 데이터를 생성합니다.
상품 데이터는 상품Id, 사용 여부, 소유자Id를 포함하고, 고유한 식별 코드를 부여받습니다.

### 마이페이지
![image](https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/02e6b7a3-4c06-40fd-947f-2c2a47b543cc)<br>
마이페이지에서는 내 프로필, 스탬프 내역, 보관함을 확인할 수 있습니다.
스탬프 내역은 전체 / 적립 / 사용 탭으로 구분해 확인할 수 있습니다.
각 내역들은 시간과 획득처 / 사용 내용 등의 정보를 포함합니다.
이 내역 목록은 무한 스크롤로 구현해 스크롤을 맨 아래로 내릴 때마다 기록을 10개씩 로딩합니다.

### 보관함
![image](https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/9ea6eab2-f021-411d-9b91-f74293af734f)
![image](https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/f2e1eea0-133e-468b-9856-b30078b0bdf9)<br>
스토어에서 구매한 상품을 보관함에서 확인할 수 있습니다. 사용가능 탭의 상품을 클릭 시 바코드를 확인할 수 있습니다.

### 프로필 수정
![image](https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/8c57bc35-b391-4318-b429-b8c794a186cb)<br>
![image](https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/5ee9c4dd-a257-4264-b022-48cdaa3756f0)
![image](https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/8026b9e0-74d2-423b-80f9-93036e5e6acd)<br>
프로필 수정 페이지에서 닉네임, 이메일, 비밀번호를 수정할 수 있고, 회원탈퇴를 할 수 있습니다.

### 관리자페이지
![image](https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/ea270de3-2d93-4541-bae7-04d33574c428)<br>
관리자로 지정된 계정으로 관리자 페이지로 이동해 관리자 기능을 이용할 수 있습니다.
보관함의 상품의 코드를 입력해 상품을 사용완료 상태로 변경할 수 있고, 스토어의 상품을 추가할 수 있습니다.


### 비밀번호 찾기
![image](https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/825c4412-8c39-421f-b293-a23a7ce257f3)
![image](https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/73e14db6-9500-4140-ba2e-2bfd0c12d40c)
![image](https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/9276c05f-6384-49ff-acde-9b827e84fd77)<br>
로그인 단계에서 비밀번호 찾기로 이동할 수 있습니다. 사용자가 아이디를 입력 시 서버는 계정에 저장 된 이메일로 인증 코드를 보냅니다.
인증 코드는 16자리 랜덤 문자열로 생성되고, DB에 시간 정보와 함께 저장됩니다.
인증 코드는 1분의 유효시간을 가지고, 사용자는 이메일을 확인해 인증 코드를 입력하고, 비밀번호를 재설정 할 수 있습니다.








![image](https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/4cfbb1e6-5184-4fd5-978a-22d0c1b77992)
