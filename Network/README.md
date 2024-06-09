# Smart Recycling System Infra Structure
Ecoarium은 단순 장치 구동을 구현한 것이 아닌 Smart Recycling System을 설계하였습니다. <br>
이 저장소는 해당 시스템의 Infra Structure와 그를 구성하는 DB서버와 DBMS, 그에 대한 System 보안을 기술합니다. <br>
- Ecoarium이 고안한 Smart Recycling System 구조는 안정성, 보안성, 성능 향상 등의 효율성 증대를 위해 AP–DB 이원화 서버를 구축하였습니다. <br>
- DB서버와 JT, Web/Mobile Application은 각각 Client로서 AP서버의 Web서버에 접속합니다. <br>
- JT, Web/Mobile Application은 DB로의 직접적인 통신이 불가하며, AP(Web) Server를 거쳐 통신합니다.
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/56341387/6aa2d28b-694a-456e-99a3-d00111a5061f" alt="Smart Recycling System 구성도" style="width: 70%;"><br>

## DB서버
Ecoarium은 단순 장치 구동을 구현한 것이 아닌 System을 설계했습니다. <br>

### 클라우드 컴퓨팅 서비스 기반 DB서버 구축
System 확장성을 위해 클라우드 컴퓨팅 서비스인 Linux CentOS7 기반의 AWS EC2 instance를 생성합니다.
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/56341387/b8f92a4e-3cda-4ebc-bff6-e1029d162d3e" alt="Linux CentOS7기반 AWS EC2 instance 생성" style="width: 70%;"><br>

### DBMS서비스 설치 및 실행
안정적이고 빠른 성능을 제공하면서도 오픈 소스 기반으로 비용 효율적인 RDBMS인 MySQL 8.0을 사용합니다.  <br>
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/56341387/2873dc99-0890-4d47-a482-cc039b6d16c1" alt="MySQL 설치 및 실행" style="width: 70%;"><br>
- MySQL 8.0 설치 명령어 <br>
yum install –y https://dev.mysql.com/get/mysql80-community-release-el7-3.noarch.rpm <br>
yum install -y mysql-community-server
- MySQL 8.0 시작 및 부팅시 자동시작 설정 명령어 <br>
systemctl start mysqld <br>
systemctl enable mysqld 
- MySQL 8.0 진입 명령 <br>
mysql –u root -p <br>
