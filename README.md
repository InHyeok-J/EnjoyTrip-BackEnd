# Tripfy
- contributors : 조인혁, 이예은, 박지환
- enjoytrip의 api
## api 배포된 url
- 위치  
https://port-0-tripfy-f69b2mlhc41lrz.sel4.cloudtype.app/attractions
- 사용 예시 (관광지 검색)  
https://port-0-tripfy-f69b2mlhc41lrz.sel4.cloudtype.app/attractions?sidoCode=38&gugunCode=128&category=12&title=%EC%82%B0&offset=1

## Attractions
### 기능
- 관광지 목록을 조회할 수 있다. (페이지네이션)
- 관광지 목록에 대해 검색된 결과 목록을 조회할 수 있다. (페이지네이션)
- 관광지 목록에서 하나를 조회할 수 있다.
- 하나의 관광지에 대해 리뷰(후기)를 작성할 수 있다.
- 하나의 관광지에 대한 리뷰 여러개를 조회할 수 있다.
- 하나의 리뷰를 삭제할 수 있다.
### api

- 관광지 조회  
GET /attractions
- 관광지 검색 목록 조회  
GET /attractions?sidoCode=38&gugunCode=128&category=12&title=산
- 관광지 리뷰 목록 조회  
GET /attractions/{attractionId}/reviews
- 관광지 리뷰 작성  
POST /attractions/{attractionId}/reviews
- 관광지 리뷰 하나 조회  
GET /attractions/{attractionId}/reviews/{reviewId}
- 관광지 리뷰 삭제  
DELETE /attractions/{attractionId}/reviews/{reviewId}

## Auth
### 기능
- 이메일 인증 보내기
- 이메일 인증 코드 확인
- 패스워드기반 로그인
- 로그아웃
### api
- POST /auth/history
- POST /auth/history/check
- POST /auth/login
- GET /auth/logout
## User
### 기능
- 회원가입
- 세션 유저 정보 조회
### api
- POST /user
- GET /user

## Course
### 기능
- 유저별(본인) 코스를 조회 할 수 있다.
- 유저별(본인) 코스의 공개 여부를 정할 수 있다.
- 조회된 코스 목록에서 하나를 조회할 수 있다.
- 하나의 코스에 상세 내용(목적지, 메모, 도착 순서 등)을 작성할 수 있다.
- 하나의 코스의 내용을 수정할 수 있다.
- 하나의 코스를 삭제할 수 있다..
- 다른 유저가 작성한 코스에 댓글을 남길 수 있다.
- 다른 유저가 작성한 코스에 좋아요를 표시할 수 있다.

### api
- 관광지 조회  
GET courses/list
- 코스 만들기  
POST courses/make
- 코스 상세 내용 넣기  
POST courses/insert
- 코스 상세 내용 수정하기  
PUT courses/change
- 코스 공개 여부 정하기  
POST courses/public
- 코스 댓글 달기  
POST courses/comment
- 코스 좋아요 누르기  
POST courses/like
