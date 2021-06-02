# GitHubUserInfo

기본 기능
 - 깃 허브 유저 리스트 조회
 	- 목록을 무한 스크롤 되는 뷰로 표시
 	- 뷰 스크롤시 로딩 대기 중일때는 프로그레스로 표시
 - 깃 허브 oAuth 로그인
 - 유저 선택시 상세한 정보 조회 가능(팔로워, 팔로잉, 스타, 레포 목록)
 	- 상세 정보 세부 페이지는 웹뷰로 구현

추가 기능
 - 유저정보, 썸네일 캐싱하여 추후 로딩 속도 증대
 - 로그인 정보 기억하여 자동로그인 기능 구현
 	- 저장 토큰 암호화

구조
- IDE: 안드로이드 스튜디오
- 언어: 코틀린
- DI: 대거2
- 아키텍처 패턴: mvvm
- DB: Room
- 네트워크: Retrofit
- 비동기: RxJava2