# 뱁새 쫓는 황새

---

## 팀소개

---

> 삼성 청년 SW 아카데미 8기 3학기 자율 프로젝트

- 이영차 (팀장, Android)
- 배창민 (B.E, Android)
- 황준현 (B.E)
- 이다운 (B.E, Infra)
- 이예진 (Android, Design)
- 이호준 (B.E)

# Maru - 서울에 새워보는 나만의 랜드마크

---

![maru_logo](./output/images/maru_logo.png)

## 프로젝트 기간

---

> 2023.04.10(월) ~ 2023.05.18(목) 6주
* 2023.04.10(월) ~ 2023.04.14(금) - 1주차
    * 아이디어 선정
* 2023.04.17(월) ~ 2023.04.21(금) - 2주차
    * 아이디어 구체화
    * 요구사항 정의서 작성
    * 와이어 프레임 제작
    * 프로토 타입 제작
    * 기능명세서 작성
    * ERD 작성
* 2023.04.24(월) ~ 2023.04.28(금) - 3주차
    * 안드로이드 구현
    * 백엔드 구현
    * 스프링 서버 배포
* 2023.05.01(월) ~ 2023.05.04(목) - 4주차
    * 안드로이드 구현
    * 백엔드 구현
* 2023.05.08(월) ~ 2023.05.12(금) - 5주차
    * 안드로이드 구현
    * 백엔드 구현
    * Elastic Search 적용
* 2023.05.15(월) ~ 2023.05.18(목) - 6주차
    * 랜딩페이지 구현
    * 안드로이드 고도화
    * 백엔드 고도화
    * E2E 테스트 시나리오 작성

## Maru - 배경

---

### 저희가 해결하려는 문제는

많은 사람들은 지루한 생활에서 벗어나기 위한 여행을 떠납니다. 하지만 여행을 떠나는 것이 마냥 쉬운 일은 아닙니다.

집과 회사를 반복하며 겪는 무료한 일상에 숨겨진 즐거움을 찾지 못하는 사람들을 위한 서비스를 기획했습니다.

### 저희가 문제에 접근하는 방식은

동네 만족도를 조사한 결과에 따르면 거주지에서의 활동을 선호하는 사람의 비율은 약 77%에 달합니다.

저희는 동네에서 활동하는 사람들이 자신의 거주지와 더 긴밀하게 상호작용 할 수 있는 환경을 제공할 것입니다.

거주지는 사람 간의 상호작용을 매개하기 때문에 최종적으로 사람 사이의 상호작용으로 확장시킬 것 입니다.

### 저희가 기대하는 효과는

익숙한 생활 반경에서 새로운 발견을 할 수 있습니다.  

새로운 발견을 통해 가까운 일상에서 재미를 추구할 수 있습니다.

## 화면

| 나만의 장소 등록                                                                                                                  | 등록된 장소 조회                                                                                                    |
| ------------------------------------------------------------------------------------------------------------------------------ | ----------------------------------------------------------------------------------------------------------------- |
| ![나만의 장소 등록](./output/images/%E1%84%82%E1%85%A1%E1%84%86%E1%85%A1%E1%86%AB%E1%84%8B%E1%85%B4%20%E1%84%8C%E1%85%A1%E1%86%BC%E1%84%89%E1%85%A9%20%E1%84%83%E1%85%B3%E1%86%BC%E1%84%85%E1%85%A9%E1%86%A8%20(1).gif)<p align="center"> </p>                             | ![등록된 장소 조회](./output/images/%E1%84%83%E1%85%B3%E1%86%BC%E1%84%85%E1%85%A9%E1%86%A8%E1%84%83%E1%85%AC%E1%86%AB%20%E1%84%8C%E1%85%A1%E1%86%BC%E1%84%89%E1%85%A9%20%E1%84%8C%E1%85%A9%E1%84%92%E1%85%AC%20(1).gif)<p align="center"> </p>    |


| 키워드 검색 기능                                                                                                                     | 위치 추적 기능                                                                                                       |
| ------------------------------------------------------------------------------------------------------------------------------ | ----------------------------------------------------------------------------------------------------------------- |
| ![키워드 검색 기능](./output/images/%E1%84%8F%E1%85%B5%E1%84%8B%E1%85%AF%E1%84%83%E1%85%B3%20%E1%84%80%E1%85%A5%E1%86%B7%E1%84%89%E1%85%A2%E1%86%A8%20%E1%84%80%E1%85%B5%E1%84%82%E1%85%B3%E1%86%BC.gif)<p align="center"> </p>              | ![위치 추적 기능](./output/images/%E1%84%8B%E1%85%B1%E1%84%8E%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8C%E1%85%A5%E1%86%A8%20%E1%84%80%E1%85%B5%E1%84%82%E1%85%B3%E1%86%BC.gif)<p align="center"> </p> |


| 마이페이지 - 경매                                                                                                                       | 실시간 경매                                                                                                          |
| ------------------------------------------------------------------------------------------------------------------------------ | ----------------------------------------------------------------------------------------------------------------- |
| ![마이페이지 - 경매](./output/images/%E1%84%86%E1%85%A1%E1%84%8B%E1%85%B5%E1%84%91%E1%85%A6%E1%84%8B%E1%85%B5%E1%84%8C%E1%85%B5_%E1%84%80%E1%85%A7%E1%86%BC%E1%84%86%E1%85%A2.gif) <p align="center"> </p>                          | ![실시간 경매](./output/images/%E1%84%89%E1%85%B5%E1%86%AF%E1%84%89%E1%85%B5%E1%84%80%E1%85%A1%E1%86%AB%20%E1%84%80%E1%85%A7%E1%86%BC%E1%84%86%E1%85%A2.gif)<p align="center"> </p>              |


### 호환 브라우저

- 안드로이드 어플리케이션


## 시스템 아키텍처

---

![maru-architecture](output/시스템_아키텍처/images/Maru_Architecture.png)

## 기술 스택

---

### 안드로이드
* kotlin 1.8.0
* compose_ui 1.4.1
* hilt 2.44
* cameraX 1.2.2
* mapbox 10.12.2
* firebase-bom 31.5.0
* coil 2.3.0
* retrofit2 2.6.4
* vico chart 1.6.5
* lottie-compose 4.2.0
* kakao sdk 2.13.0
* naver sdk 5.5.0
* google 20.5.0
* stomp 1.6.6

### 백엔드
* Java 11, Spring Boot 2.7.10
* Spring Security 2.7.5 (인증 인가 관리 프레임워크)
* OAuth2 Client 2.7.9 (소셜 로그인 프레임워크)
* JWT (사용자 인증)
* Spring Data JPA 2.7.9 (Hibernate 구현체)
* Spring Batch (대량의 데이터 처리)
* Spring Quartz (jdbcjobstore -> 클러스터링, Batch Job를 위한 스케줄러)
* Stomp Websocket 2.3.3-1
* QueryDSL 5.0.0 (JPA 쿼리를 코드로 작성하기 위한 프레임워크)
* Redis 2.7.9 (인메모리 데이터 구조 저장소)
* MySQL 8.0 (RDBMS)
* Elastic Search 7.17.9 (검색어 자동 완성)

### 인프라
* AWS EC2
* AWS S3
* Jenkins
* Docker
* Nginx

### 협업 툴
* Jira
* Gitlab
* Mattermost
* Notion

## 프로젝트 파일 구조

---

### Front-End
```
com
└── shoebill
    └── maru
        ├── model
        │   ├── data
        │   │   ├── landmark
        │   │   ├── myAuction
        │   │   ├── notice
        │   │   ├── request
        │   │   ├── search
        │   │   └── spot
        │   ├── interfaces
        │   ├── module
        │   ├── repository
        │   └── source
        ├── ui
        │   ├── component
        │   │   ├── auction
        │   │   ├── bottomsheet
        │   │   │   ├── landmark
        │   │   │   └── spotlist
        │   │   ├── camera
        │   │   ├── common
        │   │   ├── drawer
        │   │   ├── filter
        │   │   ├── mypage
        │   │   ├── notice
        │   │   └── searchbar
        │   ├── page
        │   └── theme
        ├── util
        └── viewmodel
```

### Back-End
```
com
└── bird
    └── maru
        ├── auction
        │   ├── controller
        │   │   └── dto
        │   ├── mapper
        │   ├── repository
        │   │   └── query
        │   └── service
        │       └── query
        ├── auctionlog
        │   ├── controller
        │   │   └── dto
        │   ├── mapper
        │   ├── repository
        │   │   └── query
        │   └── service
        │       └── query
        ├── auth
        │   ├── controller
        │   ├── repository
        │   └── service
        │       └── dto
        ├── cloud
        │   ├── aws
        │   │   └── s3
        │   │       └── service
        │   └── firebase
        │       └── fcm
        ├── cluster
        │   ├── geo
        │   ├── mapper
        │   └── util
        ├── common
        │   ├── aop
        │   ├── config
        │   │   ├── Batch
        │   │   ├── Quartz
        │   │   ├── WebSocket
        │   │   └── converter
        │   ├── exception
        │   ├── filter
        │   │   └── dto
        │   ├── handler
        │   ├── redis
        │   └── util
        ├── domain
        │   ├── converter
        │   └── model
        │       ├── document
        │       ├── entity
        │       └── type
        │           └── id
        ├── landmark
        │   ├── controller
        │   │   └── dto
        │   ├── mapper
        │   ├── repository
        │   │   └── query
        │   └── service
        │       └── query
        ├── like
        │   ├── repository
        │   │   └── query
        │   └── service
        │       └── query
        ├── map
        │   ├── controller
        │   │   └── dto
        │   └── service
        │       └── query
        ├── member
        │   ├── controller
        │   │   └── dto
        │   ├── mapper
        │   ├── repository
        │   │   └── query
        │   └── service
        │       └── query
        ├── notice
        │   ├── controller
        │   │   └── dto
        │   ├── model
        │   ├── repository
        │   └── service
        ├── point
        │   ├── controller
        │   └── service
        ├── scrap
        │   ├── repository
        │   │   └── query
        │   └── service
        │       └── query
        ├── spot
        │   ├── controller
        │   │   └── dto
        │   ├── mapper
        │   ├── repository
        │   │   └── query
        │   │       └── dto
        │   └── service
        │       ├── dto
        │       └── query
        └── tag
            ├── controller
            ├── repository
            │   └── query
            └── service
                └── query
```

## 프로젝트 산출물

---

* 회의록 [링크](./output/meetings)
* 컨벤션 [링크](./output/convention)
* 와이어 프레임 [링크](./output/%EC%99%80%EC%9D%B4%EC%96%B4%ED%94%84%EB%A0%88%EC%9E%84/readme.md)
* 프로토타입 [링크](./output/%ED%94%84%EB%A1%9C%ED%86%A0%ED%83%80%EC%9E%85/readme.md)
* 기능명세서 [링크](https://docs.google.com/spreadsheets/d/1QLpkB2_jkLmos_p85rNk1ySYnbv55LZ4-QxLJyU4KVI/edit#gid=0)
* ERD [링크](./output/ERD/readme.md)
* API 명세서 [링크](https://www.notion.so/6-API-939b43e287e14412abcbc50b63089aec?pvs=4)
* 시스템 아키텍처 [링크](./output/%EC%8B%9C%EC%8A%A4%ED%85%9C_%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98/readme.md)
* 발표 자료
    * [230421 Maru 중간발표.pdf ](./output/발표자료/230421_마루_중간발표.pdf)
    * [230519 Maru 최종발표.pdf](./output/발표자료/230519_마루_최종발표.pdf)
