# mop-batch
> MoP(Meeting of People)  동호회 애플리케이션 배치 서버

## Introduce
https://github.com/iyoungman/mop-android

## About
동호회장 권한을 가지고 있는 회원들에게 하루가 끝나는 특정 시간 마다 서버에서 동호회의 일일 통계를 배치를 돌린 후 성공하면 클라이언트(안드로이드)로 Push알림을 보내  확인 할 수 있도록 한다.

## Fuction
* 배치 스케줄링
  + 해당 동호회의 일별 회원가입 인원 수, 회원가입 인원의 정보, 게시글 통계정보를 DB에 저장  
  + 하루에 한번 자정에 스케줄링
* Push 알림
  + 배치 스케줄링이 성공하면 동호회장에게 Push 알림(일반 회원은 제외)
  
## Usage
* Spring Boot(Spring Starter Web, Spring Starter Batch)
* Maven(Build Tool)
* Spring Data JPA, QueryDSL
* MySQL(RDBMS)
