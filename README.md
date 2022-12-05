# 🏠 Happy House - 부동산 실거래가 조회 서비스 

### 🏆 삼성 청년 SW 아카데미 관통프로젝트 우수상 수상

![Group 437](https://user-images.githubusercontent.com/98465383/204216422-6320e908-432a-4229-931d-5f9560a22699.png)

---
<br/>

## 📆  프로젝트 진행 기간

- 2022.05.18 - 2022.05.26
- 삼성 청년 SW 아카데미 관통프로젝트

<br/>

## 🔎  프로젝트 개요

- 공공데이터 및 카카오 맵 API를 이용한 아파트 실거래가 및 주변 정보 조회 웹사이트입니다. <br/> 
<br/>

---


## 📌 주요 기술 스택
### Frontend

<p><img src="https://img.shields.io/badge/Visual Studio Code IDE: 1.69.2-007ACC?style=for-the-badge&logo=VisualStudioCode&logoColor=white">
<img src="https://img.shields.io/badge/Vue.js: 2.6.14-4FC08D?style=for-the-badge&logo=Vue.js&logoColor=white">
<img src="https://img.shields.io/badge/Bootstrap: 4.6.1-7952B3?style=for-the-badge&logo=Bootstrap&logoColor=white">
</p>

### Backend

<p><img src="https://img.shields.io/badge/SpringBoot: 2.6.7-6DB33F?style=for-the-badge&logo=SpringBoot&logoColor=white">
<img src="https://img.shields.io/badge/MySQL: 8.0.29-4479A1?style=for-the-badge&logo=MySQL&logoColor=white">
<img src="https://img.shields.io/badge/Java: Zulu 1.8-007396?style=for-the-badge&logo=java&logoColor=white">
</p>

<br/>

## 💡 주요 기능
### 1. 회원관리
<span>
<img width=49% src="https://user-images.githubusercontent.com/98465383/186188983-4427a540-eef6-4768-a832-83a4d6261e46.png">
<img width=49% src="https://user-images.githubusercontent.com/98465383/186189476-3933d567-811c-4339-90c0-5c0285c159c9.png">
</span>



### 

- jwt 를 이용하여 access token 을 서버에서 생성하고, 회원의 세션스토리지에 저장하는 형식으로 로그인 기능을 구현.
- 회원가입 시, 정규표현식을 사용하여 아이디, 비밀번호, 이름 입력 시 유효성을 검사.
- axios 를 이용하여 아이디 중복 여부 검사.
- 비밀번호 찾기는 가입한 아이디, 이메일, 이름을 입력하여 서버에 있는 정보인 경우 가입한 이메일로 임시 비밀번호 전송.

<br/>

### 2. 아파트 실거래가 검색 및 상세정보 조회

#### 1) 아파트 검색 페이지

<span>
<img width=49% src="https://user-images.githubusercontent.com/98465383/186190437-2b97acd8-40a7-40fd-bcd8-c7b486e97998.png">
</span>

#### 2) 아파트 주변정보 상세페이지

<span>
<img width=49% src="https://user-images.githubusercontent.com/98465383/186191289-ecd7cfed-90c2-4411-b596-d48e7726aa0f.png">
<img width=49% src="https://user-images.githubusercontent.com/98465383/186190461-a6a960a7-58c1-404b-8218-75ceeb70c4dd.png">
<img width=49% src="https://user-images.githubusercontent.com/98465383/186190475-39303001-ee6c-42bd-90f4-76485a778196.png">
<img width=49% src="https://user-images.githubusercontent.com/98465383/186190482-df1ad0b7-94ad-4ec0-bdd9-f72cd646e796.png">
</span>

###

- 카카오 맵 API를 이용하여 지도 레벨 수준에 따라 클러스터링, 마커를 표시.
- 지도의 레벨이 낮아질수록(지도가 확대될 수록) 아파트 이름, 가격, 평수 정보를 담은 커스텀
오버레이로 마커 표시.
-사용자가 지도를 이동할 때 마다 지도의 남서쪽, 북동쪽 정보를 바탕으로 해당 영역에 있는
아파트 정보를 비동기 통신으로 DB 에서 얻어와 마커로 표시.
- 지도에 표시된 마커를 클릭시 해당 아파트의 정보, 최근 실거래가 및 거래 내역, 주변 대비 평균
가격, 주변 환경시설 관련정보(교육, 교통, 마트 등)를 별도의 창으로 표시.

<br/>

### 3. 아파트 검색 필터링 및 관심 아파트 등록

<span>
<img width=49% src="https://user-images.githubusercontent.com/98465383/186191974-8f1363e7-098c-4953-9533-5acac4680f2a.png">
<img width=49% src="https://user-images.githubusercontent.com/98465383/186192741-42c22b06-b5bc-4438-a4df-8670b2b35d19.png">
</span>

###

- 평수, 거래 가격을 기준으로 지도 내 표시된 아파트 목록 필터링 기능.
- 유저가 등록한 관심 아파트는 5개까지 저장 가능하며, 관심 목록에 있는 아파트 클릭시 지도가
해당 위치로 이동.

<br/>

### 4. Q&A, 공지사항 게시판

<span>
<img width=49% src="https://user-images.githubusercontent.com/98465383/186192042-02579664-957b-4097-ada9-5e9bf3f84454.png">
<img width=49% src="https://user-images.githubusercontent.com/98465383/186192042-02579664-957b-4097-ada9-5e9bf3f84454.png">
</span>

###

- 공지사항 게시판은 관리자 계정만 글쓰기/수정/삭제 가능.
- 게시글, 댓글의 작성자가 현재 로그인한 이용자와 같은 경우에만 수정/삭제 가능.

<br/>

### 5. 뉴스 게시판

<span>
<img width=49% src="https://user-images.githubusercontent.com/98465383/186192027-0b431679-ea23-45b3-aae2-9020331cd42d.png">
</span>

###


- jsoup를 활용하여 실시간으로 네이버 부동산 뉴스를 크롤링.
- 제목 클릭시 해당 뉴스 페이지로 이동.

<br/>

## ✏️ 배운점
<p>

#### [컴포넌트 통신에 대한 이해도 증가]<br>

프로젝트를 진행하면서 직접 필요한 컴포넌트들을 설계하고, 적용해보면서 Vue.js의 컴포넌트 간 통신를 더욱 이해할 수 있었고, 코드 작성이 훨씬 수월하 게 느껴졌다. 재사용 가능한 컴포넌트 설계와 함수형 프로그래밍을 통해 코드 의 가독성과 재사용성을 높이는 것의 중요성을 알게 되었다.
</p>
<p>

#### [데이터 역정규화를 통한 성능 개선]<br>

SQL 쿼리문을 join을 이용하여 데이터를 가져와야 했지만, 아파트 실거래가 테 이블의 데이터가 약 470만 건이 넘어 렌더링이 오래 걸리는 문제가 발생했다. 아파트 정보 테이블에 최근 거래가격, 날짜를 추가하는 역정규화를 통해 개선 하였다.
</p>
<p>

#### [협업 및 소통능력 향상]<br>

팀으로 프로젝트를 진행하면서 매일 아침 스크럼회의를 진행했다. 서로의 작업 내용을 공유하며 업무 분담을 하였고, git을 이용하여 코드 관리를 하였다. 작 성한 코드에 대한 피드백을 팀원과 주고 받았다. 더 나은 코드로 개선하기 위 해 고민하고, 오류가 발생할 때 같이 해결하는 과정을 통해 협업의 중요성을 느낄 수 있었다.
</p>
