# SDET2023
## Тесты для экрана 1920, 1080
На расширениях 800-600 и 1280-1024 не проходят в jobe githuba из-за рекламы.
* Отчет на гит хаб pages.
https://egor18032019.github.io/SDET2023/
---
Установить мавен(если есть то пропустить этот пункт)
```shell
sudo apt-get install maven
```
Установить allure(если есть то пропустить этот пункт)
```shell
wget https://github.com/allure-framework/allure2/releases/download/2.25.0/allure_2.25.0-1_all.deb
sudo dpkg -i allure_2.25.0-1_all.deb
sudo rm -rf allure_2.25.0-1_all.deb
```
Перейти в папку проекта.

Запустить тесты
```shell
mvn -B test --file pom.xml
```

Отобразить отчет allure
```shell
cd target/
allure serve
```

Сделать;
добавить java-faker ?