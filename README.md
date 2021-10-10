# thePirates

1. 설치 및 환경설정 가이드

> 1. clone후 Gradle bootjar task수행
> 2. Dockerfile이 있는 폴더에서 터미널을 열고 **docker build -t {yourImageTag} .** 스크립트 수행
> 3. **docker run -it -p 8080:8080 {yourImageTag}** 스크립트 수행
> 

2. 테이블 생성 SQL  
AutoDDL 설정을 사용

3. API 사용 가이드
> 1번 docker 컨테이너를 통해 스프링 jar 프로젝트 실행후 Localhost로 요청  

### POST: /product
#### product 생성 Request.json
```json
{
  "name": "노르웨이산 연어",
  "description": "노르웨이산 연어 300g, 500g, 반마리 필렛",
  "delivery": {
    "type": "fast",
    "closing": "12:00",
    "price": 10000
  },
  "supplier": {
    "open": "10:00",
    "close": "20:00"
  },
  "options": [
    {
      "name": "생연어 몸통살 300g",
      "price": 10000,
      "stock": 99
    },
    {
      "name": "생연어 몸통살 500g",
      "price": 17000,
      "stock": 99
    }
  ]
}
```
#### 응답
```json
{
    "id": 1
}
```

### GET: /product?id={productId} 
#### productDetail 쿼리

#### 응답
```json
{
    "name": "노르웨이산 연어",
    "description": "노르웨이산 연어 300g, 500g, 반마리 필렛",
    "deliveryType": "FAST",
    "options": [
        {
            "name": "생연어 몸통살 300g",
            "price": 10000,
            "stock": 99
        },
        {
            "name": "생연어 몸통살 500g",
            "price": 17000,
            "stock": 99
        }
    ]
}
```

### GET: /products
#### product 쿼리

#### 응답
```json
{
    "prodcutInfos": [
        {
            "name": "노르웨이산 연어",
            "description": "노르웨이산 연어 300g, 500g, 반마리 필렛",
            "price": "10,000 ~ "
        },
        {
            "name": "제주 전복",
            "description": "산지직송 완도 전복 1kg (7미~60미)",
            "price": "30,000 ~ "
        }
    ]
}
```

### DELETE: /product?id={productId}
#### product 제거

### GET: /rcvDate?id={productId}
#### 해당상품 발송가능일 쿼리

### 응답
```json
{
    "receivingDateInfos": [
        {
            "date": "10월 12일 화요일"
        },
        {
            "date": "10월 13일 수요일"
        },
        {
            "date": "10월 14일 목요일"
        },
        {
            "date": "10월 15일 금요일"
        },
        {
            "date": "10월 16일 토요일"
        }
    ]
}
```