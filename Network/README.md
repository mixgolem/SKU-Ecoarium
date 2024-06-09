# Smart Recycling System Infra Structure
Ecoarium이 고안한 Smart Recycling System 구조는 안정성, 보안성, 성능 향상 등의 효율성 증대를 위해 AP–DB 이원화 서버를 구축하였습니다. <br>
DB서버와 JT, Web/Mobile Application은 각각 Client로서 AP서버의 Web서버에 접속합니다. <br>
JT, Web/Mobile Application은 DB로의 직접적인 통신이 불가하며, AP(Web) Server를 거쳐 통신합니다.
  <img src="https://github.com/mixgolem/SKU-Ecoarium/assets/153111259/f94d3ea3-e877-4cae-b073-a98769a674f0" alt="Ecoarium 네트워크 구성도" style="width: 70%;">
