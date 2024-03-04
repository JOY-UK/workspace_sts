# 실행:Shift + Enter
# 라이브러리 임포트
import requests
import pandas
import bs4

# API로 데이터 불러오기

url = 'http://apis.data.go.kr/3210000/SeochoIaqSvc/getSeochoIaqRtData'
params ={'serviceKey' : 'pyObvpPAUjebTmRqZpT+p0ivJxkPifmMrJ2zf2ZhkA6vau1ePB+BHNdaI/JTm/VLxTAFbsjszJuwtUoGtstdKA==', 
         'numOfRows' : '10', 
         'pageNo' : '1' }

response = requests.get(url, params=params)

# 가져온 데이터(문자열)를 bueatifulSoup 형태로 변환
bs_data = bs4.BeautifulSoup(response.text, 'lxml-xml')

# item태그의 모든 내용만 가져 옴
items = bs_data.find_all('item')

# items 안의 내용을 통해 딕셔너리 데이터를 구성
dataTime_list = []
for item in items :
  dataTime_list.append(item.find('dataTime').text)

# api로 가져온 데이터를 모두 저장할 딕셔너리
dic_data = {}
dic_data['dataTime'] = dataTime_list

# 딕셔너리 데이터를 DataFrame로 변환
df = pandas.DataFrame(dic_data)
df.to_excel('api_text.xlsx')
print(df)




