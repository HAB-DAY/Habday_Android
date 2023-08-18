# 🎁 서비스 소개

카카오톡으로 기프티콘 선물 받기, 너무 진부한 생일이지 않나요? HABDAY를 이용해 친구들에게 선물 펀딩을 받아보세요!

<p align = "center">
<img src="https://github.com/HAB-DAY/Habday_Web/assets/65955748/514070c4-c7c6-472a-9dcc-24dd3eca0474" width="200" height="200"/> 
</p>

HABDAY는 친구들과 함께하는 선물 펀딩 플랫폼입니다.<br/>
자신이 원하는 선물을 친구들에게 펀딩을 받고, 그동안 갖고 싶었던 고가의 선물을 구매할 수 있습니다.<br/>
뿐만 아니라, 친구들의 도움으로 꿈을 실현할 수도 있는 혁신적인 플랫폼입니다. 

<br/>

# 🛠 사용기술 및 라이브러리
`Android Studio` `Kotlin` `Retrofit2` `Okhttp3` `ViewBinding` `Glide`

<br/>

# 📌 기능 및 뷰 설명

## 로그인 뷰

<img src="https://github.com/HAB-DAY/Habday_Android/assets/81751105/18ce2e79-7cdc-4d91-90af-5c18b4a6f542" width="200" /> 
<img src="https://github.com/HAB-DAY/Habday_Android/assets/81751105/65c2cd73-a123-4b41-ae81-8e495383e64b" width="200" /> 

- 참여자가 생성자가 공유한 링크로 진입하게 되면, 로그인 화면을 보여준다.
- `네이버로 시작하기` 버튼을 클릭하면, 네이버로그인 링크로 접속한다.
- 참여자가 네이버 아이디와 비밀번호를 입력하여 로그인에 성공하면 인가코드를 발급받는다.
- 발급받은 인가코드를 서버에 전달해, 액세스 토큰을 발급한다.
- 발급받은 액세스 토큰은 앞으로의 서버 요청 시 headers에 넣어 사용자 식별에 사용된다.
- 만약 최초로 로그인한 사용자이면, 추가 정보를 입력하여 가입을 완료한다.

<br/>

## 메인 뷰(내가 받은 선물, 받고 싶은 선물, 내가 준 선물)
<img src="https://github.com/HAB-DAY/Habday_Android/assets/81751105/f44418aa-3d25-4c18-8c2f-f9a65b7002c6" width="200" /> 

- 메인 뷰에서 사용자의 생일까지 남은 날짜 수를 보여준다.
- '내가 받은 선물' 탭에서는 사용자가 지금까지 펀딩을 생성해 성공하여 받은 선물 리스트를 확인할 수 있다.
- '받고 싶은 선물' 탭에서는 사용자가 받고 싶은 선물이 있어 생성한 펀딩 리스트를 확인할 수 있다.
- '내가 준 선물' 탭에서는 사용자가 다른 사용자에게 선물을 한 리스트를 확인할 수 있다.

<br/>

## 펀딩 생성, 수정 뷰 및 생성 완료 뷰
<img src="https://github.com/HAB-DAY/Habday_Android/assets/81751105/f7263786-df28-454f-9ef5-169e403811ff" width="200" /> 
<img src="https://github.com/HAB-DAY/Habday_Android/assets/81751105/9003c3ca-d77b-4339-ac05-6bb772e9d96d" width="200" /> 
<img src="https://github.com/HAB-DAY/Habday_Android/assets/81751105/8505c2b4-1b43-460f-a7f8-43378e2970d8" width="200" /> 


- '펀딩 생성하기' 뷰에서는 받고 싶은 선물에 대한 펀딩 이름, 선물 이미지, 해당 선물의 가격, 선물 받고 싶은 금액, 선물을 받기 시작할 날짜와 해당 선물에 대한 설명을 입력해야 한다.
- '펀딩 수정하기' 뷰에서는 선물 받고 싶은 금액, 선물 받을 기간을 제외한 나머지 내용을 수정할 수 있다.
- 펀딩 생성을 완료하면 '링크 공유하기' 버튼으로 해당 선물에 대한 링크를 복사할 수 있어 친구들에게 링크를 공유할 수 있다.


  <br/>

## 펀딩 상세보기 뷰
<img src="https://github.com/HAB-DAY/Habday_Android/assets/81751105/7594d79d-47b4-40c4-838c-6e822ea3ebb1" width="200" /> 


- '펀딩 상세보기' 뷰에서는 해당 상품의 이미지, 선물 이름, 선물 기간, 상세 내용 등 및 선물을 준 친구의 목록을 메세지와 함께 확인할 수 있다.

  <br/>

## 펀딩 인증하기 뷰
<img src="https://github.com/HAB-DAY/Habday_Android/assets/81751105/6cc754b4-271d-4c19-8459-4897fe86de68" width="200" /> 

- 펀딩에 성공하면 사용자의 생일날부터 2주 동안 선물 받은 금액으로 원했던 선물을 구매하고 인증해야 한다.
- 해당 선물을 구매한 이미지와 감사의 메세지를 입력한다.
