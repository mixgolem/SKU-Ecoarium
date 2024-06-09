# Smart Recycling System Infra Structure
Ecoarium은 단순 장치 구동을 구현한 것이 아닌 Smart Recycling System을 설계하였습니다. <br>
이 저장소는 해당 시스템의 Infra Structure와 그를 구성하는 DB서버와 DBMS, 그에 대한 System 보안을 기술합니다. <br>
- Ecoarium이 고안한 Smart Recycling System 구조는 안정성, 보안성, 성능 향상 등의 효율성 증대를 위해 AP–DB 이원화 서버를 구축하였습니다. <br>
- DB서버와 JT, Web/Mobile Application은 각각 Client로서 AP서버의 Web서버에 접속합니다. <br>
- JT, Web/Mobile Application은 DB로의 직접적인 통신이 불가하며, AP(Web) Server를 거쳐 통신합니다.
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/56341387/51d32200-bd0b-4ac3-b3a5-a4711640b3ba" alt="Smart Recycling System 구성도" style="width: 70%;"><br>

## DB서버
Ecoarium은 단순 장치 구동을 구현한 것이 아닌 System을 설계했습니다. <br>

### 클라우드 컴퓨팅 서비스 기반 DB서버 구축
System 확장성을 위해 클라우드 컴퓨팅 서비스인 Linux CentOS7 기반의 AWS EC2 instance를 생성합니다.
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/56341387/7ecbe3b9-9f80-41e2-b9c8-bd28d072f411" alt="Linux CentOS7기반 AWS EC2 instance 생성" style="width: 70%;"><br>

### DBMS서비스 설치 및 실행
안정적이고 빠른 성능을 제공하면서도 오픈 소스 기반으로 비용 효율적인 RDBMS인 MySQL 8.0을 사용합니다.  <br>

- MySQL 8.0 설치 명령어 <br>
yum install –y https://dev.mysql.com/get/mysql80-community-release-el7-3.noarch.rpm <br>
yum install -y mysql-community-server <br>
- MySQL 8.0 시작 및 부팅시 자동시작 설정 명령어 <br>
systemctl start mysqld <br>
systemctl enable mysqld <br>
- MySQL 8.0 진입 명령 <br>
mysql –u root -p <br>
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/56341387/b674475c-4130-475d-a25d-ae20cf860fcf" alt="MySQL 설치 및 실행" style="width: 70%;"><br>

### ecoarium 스키마 생성
- 쿼리문 <br>
CREATE DATABASE IF NOT EXISTS ecoarium; <br>
USE ecoarium; <br>

### 주의사항
테이블링과 실제 DB서버에 적재되는 데이터 CRUD는 Ecoarium AP(Web)서버 Back-End에서 Node.js 라이브러리인 Sequelize에 의해 실행됩니다. <br>
따라서, AP서버와의 연동을 위해 root계정이 아닌 원격접속용 계정(new_username)을 생성합니다. <br>

- 원격접속용 계정 생성 및 권한부여 쿼리문 <br>
CREATE USER 'new_username'@'localhost' IDENTIFIED BY 'new_password'; <br>
GRANT ALL PRIVILEGES ON *.* TO 'new_username'@'localhost' WITH GRANT OPTION; <br>

이후, DB서버와 AP(Web)서버의 시퀄라이즈 연동을 위해 node.js Web서버 config.json파일의 적색 부분을 다음과 같이 수정합니다. <br>
이 때, 주의할 점으로 host의 ip(dns)주소 인스턴스의 내부ip주소인 public ip(dns)로 접속합니다.<br>
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/56341387/27e34ee6-301f-47ee-bca8-3d160c00a65e" alt="MySQL 설치 및 실행" style="width: 70%;"><br>

## Security
Ecoarium은 기밀성과 무결성이 보장되어야 하는 중요한 사용자 데이터를 안전하게 관리합니다. <br> 
중요한 사용자 정보가 저장되는 DB서버와 DBMS(MySQL)에 보안체크리스트(정책)를 작성하고, 취약점 점검 스크립트를 실행하여 보이지 않는 위협 또한 제거합니다. <br>
이 저장소 내의 Ecoarium DB 서버 보안점검리스트.pdf 파일은 해당 내용을 기술합니다.
