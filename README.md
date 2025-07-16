# 🏪 편의점 POS 시스템

사원 로그인을 통해 입장하여, 제품 관리, 결제, 매출 조회까지 처리할 수 있는 콘솔 기반 POS 시스템입니다.  
Oracle DB와 Java JDBC를 활용하여 재고 관리부터 판매 내역 저장까지 전 과정이 연동됩니다.

<br/>

## 📌 주요 기능

### ✅ 사원 로그인
- 사원 ID와 비밀번호를 검증하여 POS 시스템에 접근

### ✅ 제품 관리
- 제품 등록 (제품명, 제조사, 유통기한, 19금 여부, 수량 입력)
- 제품 목록 전체 조회
- 재고 수량 확인
- 제품명 또는 ID로 검색
- 입고 처리 기능 (수량 추가)

### ✅ 판매 처리
- 제품 선택 → 수량 입력 → 유효성 검사
- 결제 방식 선택 (카드 / 현금)
- 구매 내역 SALES, SALES_ITEM 테이블에 저장
- 결제 후 잔고 계산 및 출력

### ✅ 결제 처리
- 카드번호 유효성 검증 (16자리)
- 현금 입력 시 부족한 금액 검증 및 거스름돈 계산
- 카드/현금에 따른 잔고 증감 처리

### ✅ 매출 조회
- 날짜 입력 시 해당 날짜의 매출 내역 출력
- 총 매출 금액 및 상세 이력 조회
- SQL Injection 방지를 위한 PreparedStatement 사용

<br/>

## 📊 시스템 흐름도 (순서도)

> 전체 로직을 시각적으로 요약한 순서도는 추가 예정

<br/>

## 🎥 시연 영상
<img width="141" height="141" alt="image" src="https://github.com/user-attachments/assets/5ca86d6c-57b5-4119-9c1c-8e1d57fb6ede" />

👉 [유튜브 시연 영상 보러가기](https://youtu.be/6yR4x5Z8FTA?si=SfxV2d8ZBIs-gXYl)  

<br/>

## 🛠 사용 기술

| 항목         | 내용                         |
|--------------|------------------------------|
| Language     | Java                         |
| Database     | Oracle 11g                   |
| DB 연동       | JDBC                         |
| IDE          | IntelliJ IDEA / VSCode 등    |
| Version Control | Git / GitHub              |

<br/>

## 📂 프로젝트 구조

```bash
📁 src/
├─ 📁 controller/         # 메인 실행 로직
├─ 📁 dao/                # DB 접근 객체
├─ 📁 db/                 # DB 연결 클래스
├─ 📁 main/              # 메인 실행 클래스
├─ 📁 model/              # DTO 클래스 (Product, LoginUser 등)
└─ 📁 service/            # 비즈니스 로직 서비스
