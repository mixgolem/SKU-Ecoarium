# Ecoarium AP Server

## 소개
Ecoarium 프로젝트는 사용자가 JT기기(수거 기기)를 통해 일회용 플라스틱 컵을 반납하여 스탬프를 적립받는 서비스입니다.
Ecoarium의 AP Server 파트는 node.js로 개발하여 사용자의 서비스 이용을 위한 웹 서버와 모바일 어플리케이션 서비스를 위한 API, 그리고 JT기기와 통신하는 API 및 머신러닝 모델을 포함하고 있습니다.<br><br>
![상세](https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/0490472c-6a0a-4084-bd10-e61d7546c4cd)<br><br>

## 요약
사용자는 웹을 통해 계정을 생성하여 로그인하고 QR코드를 생성해 JT 기기에 인식합니다. QR코드 값을 인식한 JT 기기는 서버에 HTTP 통신을 통해 인증을 요청하고, 서버는 DB에서 유저정보를 조회하여 응답합니다.
그 후 사용자는 JT기기에 컵을 투입하고, JT기기는 컵을 촬영하여 서버에 측정을 요청합니다. 요청받은 서버는 머신러닝 모델을 실행해 사진을 측정하여 결과값을 응답하고, 깨끗하다고 판단한 경우 인증한 사용자의 DB 데이터의 스탬프 값을 증가시키고 로그를 생성합니다.
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
머신러닝 모델을 실행하여 컵의 청결도를 측정하고, 측정에 실패한 경우 사진 전송 오류, 모델 실행 오류, 모델 결과값 오류에 따라 정해진 오류 코드를 응답합니다. 측정에 성공한 경우 결과값을 응답하고, 깨끗하다고 판단한 경우 인증한 사용자의 DB 데이터의 스탬프 값을 증가시키고 로그를 생성합니다.

### 회원가입 / 로그인 기능
![image](https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/30cf9f52-bb62-4b3c-9c85-929bb3d8fd45)
![image](https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/7323eb77-3e50-4b86-9280-1264cbd80afe)<br>
로그인, 회원가입 페이지. passport 모듈을 사용해 로그인 기능을 구현하였습니다.
회원가입 성공시 DB 유저 테이블에는 아이디와 비밀번호, 이메일, 닉네임이 저장됩니다.



![image](https://github.com/mixgolem/SKU-Ecoarium/assets/163653941/4cfbb1e6-5184-4fd5-978a-22d0c1b77992)
