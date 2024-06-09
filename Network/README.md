# Smart Recycling System Infra Structure
Ecoarium이 고안한 Smart Recycling System 구조는 안정성, 보안성, 성능 향상 등의 효율성 증대를 위해 AP–DB 이원화 서버를 구축하였습니다. <br>
DB서버와 JT, Web/Mobile Application은 각각 Client로서 AP서버의 Web서버에 접속합니다. <br>
JT, Web/Mobile Application은 DB로의 직접적인 통신이 불가하며, AP(Web) Server를 거쳐 통신합니다.
  ![image](https://github.com/mixgolem/SKU-Ecoarium/assets/56341387/6aa2d28b-694a-456e-99a3-d00111a5061f)
