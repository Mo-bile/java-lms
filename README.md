# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)


# 1단계 : 레거시 코드 리팩터링
## 리팩터링 요구사항
- nextstep.qna.service.QnaService의 `deleteQuestion()`는 앞의 질문 삭제 기능을 구현한 코드이다. 
  - 이 메소드는 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드가 섞여 있다.
- QnaService의 `deleteQuestion()` 메서드에 단위 테스트 가능한 코드(핵심 비지니스 로직)를 도메인 모델 객체에 구현한다.
- QnaService의 비지니스 로직을 도메인 모델로 이동하는 리팩터링을 진행할 때 TDD로 구현한다.
- QnaService의 `deleteQuestion()` 메서드에 대한 단위 테스트는 src/test/java 폴더 `nextstep.qna.service.QnaServiceTest`이다. 
  - 도메인 모델로 로직을 이동한 후에도 QnaServiceTest의 모든 테스트는 통과해야 한다.

## 리팩터링 절차
1. 테스트하기 쉬운 부분(비즈니스 로직)과 어려운 부분(날짜, 셔플, 랜덤, DB, API call)을 구분해보기
2. 분리 시 먼저 단위테스트 추가
3. 관련 비즈니스 로직을 도메인 객체로 이동


## 리팩터링 계획
1. `List<Answer>` 의 일급컬렉션으로 래핑 및 주요 로직 이동
   - [x] : 이동 및 테스트 완료
2. `List<DeleteHistory>` 의 일급컬렉션으로 래핑 및 주요 로직 이동
    - [x] : 이동 및 테스트 완료
3. `Question` 의 주요 로직 이동
    - [x] : 이동 및 테스트 완료
4. 불필요한 setter 제거
5. 인스턴스 변수 수 줄이기
