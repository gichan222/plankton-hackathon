# 플랑크톤 해커톤

![스크린샷 2024-11-20 22 10 08](https://github.com/user-attachments/assets/df05591c-2b7a-461f-bead-2548cb7a5f75)


## 회고

2박 3일(정확한 시간은 금용일 18시~ 일요일 12시) 사이에 기획과 디자인 개발까지 끝마쳐야했던 해커톤이었다. 

해커톤 자체가 처음이기도 했고, 프론트 한 분을 빼면 처음 보는 팀원들과 했기에 결정 과정 등 걱정이 많았다.

그래도 능력있는 팀원들을 만났고, 나름 선배(?)라고 믿고 따라와준 덕분에 좋은 결과물을 만들어낼 수 있었다.

선배였던 형님의 실력을 보며 부족함을 깨닫고, 후배였던 팀원들을 보며 열정을 느끼며 고되지만 행복한 시간을 보냈다.

## 발전해야할 부분

실력적 발전보다는 이전의 작성했던 코드들을 얼마나 내 것으로 만들어 자유자재로 사용할 수 있는지가 중요한 시간이었다.

이번에 느낀 것은 언제든 나보다 잘하는 팀원이 있었다면, 혹은 나보다 뭐 하나라도 잘 작성한 코드가 있다면 무조건 내걸로 남겨두자!!!

또한, 아직 제한된 시간에 한해서는 좀 더 효율적인 아키텍처와 객체지향적 사고를 하는 방법을 모르는 것 같다.

더욱 많은 경험을 쌓고 공부를 해서 다시 한 번 나가보고 싶다.

---
## 기획
![스크린샷 2024-11-20 22 10 56](https://github.com/user-attachments/assets/84088b3b-4e08-4037-85f7-d9920fd1ad25)
![스크린샷 2024-11-20 22 11 35](https://github.com/user-attachments/assets/63ebfb4e-35a3-42ce-8dee-3e11340886cc)
![스크린샷 2024-11-20 22 11 47](https://github.com/user-attachments/assets/510e4b28-1cf0-4759-b75b-7be9b6d858da)

- 한국을 방문한 외국인이 한정된 콘텐츠를 소비하며 정보를 얻는 어려움이 있다.
- 미션을 제공하고 이에 대한 리워드를 제공함으로써 다양한 지역의 여러 체험을 하는 경험을 제공한다.
- 게임 시작 시 정해진 구/카테고리에 따라 팀이 나누어지며 미션이 종료되면 상대 팀의 미션을 맞추어 리워드를 획득할 수 있다.
- 전 날 게임에 참여했으나 아직 상대 팀 미션 맞추기에 참여하지 않았다면 오늘의 미션에 참여하는 것이 불가능하다.

---

## 기술 스택
#### Environment
![js](https://img.shields.io/badge/IntelliJ_IDEA-000000=?style=for-the-badge&logo=intellijidea&logoColor=white)
![js](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white)
![js](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)

#### Config
![gradle](https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)

#### Development
![java](https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white)
![springboot](https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![mysql](https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

#### release
![amazonec2](https://img.shields.io/badge/amazonec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white)

---

## 아키텍처
![백엔드 아키2텍처 drawio](https://github.com/user-attachments/assets/3f1b2ac3-0fe2-4728-a8cc-b506017c5018)

실제로는 시간의 문제로 인해 db를 private subnet를 두지는 못했다.

---

## 협업
<img width="1432" alt="KakaoTalk_Photo_2024-11-20-22-05-21" src="https://github.com/user-attachments/assets/6fd9ef11-6c0c-4789-a774-4c2fbe949dbe">

<img width="1435" alt="KakaoTalk_Photo_2024-11-20-22-06-07" src="https://github.com/user-attachments/assets/b1375f84-0550-4438-b0f9-fee7b0b24ac1">


정확한 API 명세를 작성할 시간이 부족하여 대화와 스웨거를 통해 API를 연동해나갔다.

---

## 개발
- 기본적으로 domain - global 디렉토리 구조를 사용하여 작성했다.
- 이전에 사용한 interceptor를 활용한 로그인 구현을 통해 로그인 처리를 간단하게 만들었다.
- 지속적으로 수정되는 기획으로 인해 완벽한 ERD를 작성하지 못했지만, 최대한 수정이 없으며 확장 가능성을 고려하였다.
