
## 프로젝트 구조 - MVVM
- api 명세



ex) users/login
    - users/signup



- data
  - remote
    - network
      - interceptor
        - TokenInterceptor.kt
      - api
        - ApiProvider.kt
        - UserApi.kt
        - ChatApi.kt
    - response
      - LoginResponse.kt
    - request
      - LoginRequest.kt
    - exception
      - NotFoundException.kt
      - UnauthorizedException.kt
      - ConflictException.kt
  - local
    - Key.kt
    - 


- feature(화면 단위)
  - MainActivity.kt
  - login
    - LoginFragment.kt
    - LoginViewModel.kt
    - LoginViewModelFactory.kt
  - signup
  - home
    - ..
  - mypage
    - ..
- util
  - 2022-03-12T00:00:00Z00 -> 2022년 03월 XX일
  - 
