# Docker 環境構築手順
## 前提
* Windows 10
* Docker Toolbox
  
## "Oracle Database 12c Release 2"のバイナリファイルをダウンロード。
http://www.oracle.com/technetwork/database/enterprise-edition/downloads/index.html  
-> Linux x86-64
  
## "Oracle JRE Server 8"のバイナリファイルをダウンロード。
http://www.oracle.com/technetwork/java/javase/downloads/server-jre8-downloads-2133154.html  
-> server-jre-8u231-linux-x64.tar.gz
  
## "Oracle WebLogic Server 12.2.1.3"のバイナリファイルをダウンロード。
https://www.oracle.com/middleware/technologies/weblogic-server-downloads.html  
-> Oracle WebLogic Server 12.2.1.3  
-> Quick Installer for Mac OSX, Windows and Linux  
  
## 仮想マシンの作成
```sh
docker-machine create \
    -d virtualbox \
    --virtualbox-disk-size 40000 \
    --virtualbox-memory 4096 \
    --virtualbox-cpu-count 2 \
    machine-1
    
docker-machine regenerate-certs machine-1
docker-machine upgrade machine-1
eval `docker-machine env machine-1`
```
  
## バイナリファイルの配置
Docker イメージのビルド用ディレクトリに、ダウンロードしたバイナリファイルを配置する。  
パスは環境に応じて変更すること。  
  
```sh
cd 'C:\Users\kator\repo\tmp\'
git clone https://github.com/oracle/docker-images

cp -vip 'C:\Users\kator\Downloads\linuxx64_12201_database.zip' \
    'C:\Users\kator\repo\tmp\docker-images\OracleDatabase\SingleInstance\dockerfiles\12.2.0.1'

cp -vip 'C:\Users\kator\Downloads\server-jre-8u231-linux-x64.tar.gz' \
    'C:\Users\kator\repo\tmp\docker-images\OracleJava\java-8'

cp -vip 'C:\Users\kator\Downloads\fmw_12.2.1.3.0_wls_quick_Disk1_1of1.zip' \
    'C:\Users\kator\repo\tmp\docker-images\OracleWebLogic\dockerfiles\12.2.1.3'
```
  
## Docker コンテナの起動
```sh
cd 'C:\Users\kator\repo\tmp\docker-20200106'
docker-compose up -d
```
  
## WebLogicの管理コンソールにアクセス
`http://${仮想マシンのIP}:${管理サーバーのポート}/console`  
  
例）  
http://192.168.99.100:7001/console  
  
アカウント情報は、`domain.properties`を参照のこと。
  
## アプリケーションのデプロイ
-> ドメイン構造  
-> デプロイメント  
-> インストール  
-> デプロイ対象の、warファイルをアップロードし、選択して次へ  
  
->  以下のように選択して次へ (デフォルトのままでOKなはず)  
  
```txt
インストール・タイプ: このデプロイメントをアプリケーションとしてインストールする
スコープ: グローバル
```
  
-> 以下のように選択して次へ
  
```txt
デプロイ・ターゲット: クラスタ -> ${対象のクラスタ}
```
  
-> オプション設定画面  
-> デフォルトのまま、次へ  
-> 選択項目の確認画面  
-> デフォルトのまま、終了  
  
## アプリケーションにアクセス
`http://${仮想マシンのIP}:${管理対象サーバーのポート}/${コンテキストルート}/`  
  
例）
http://192.168.99.100:49154/benefits/  
  
# Tips
## コンテナ／ネットワーク／イメージ／ボリューム を全て作り直したい
```sh
cd 'C:\Users\kator\repo\tmp\docker-20200106'
docker-compose down
docker system prune -a --volumes
rm -vrf 'C:\Users\kator\repo\tmp\docker-20200106\domain-1\domains'
rm -vrf 'C:\Users\kator\repo\tmp\docker-20200106\domain-2\domains'
rm -vrf 'C:\Users\kator\repo\tmp\docker-20200106\oracle-db'
docker-machine restart machine-1
eval `docker-machine env machine-1`
docker-machine regenerate-certs machine-1
docker-compose up -d
```
  
## 不要なリソースを削除したい
--- 仮想マシンの削除 ---
```sh
docker-machine rm machine-1
```
--- コンテナ／ネットワーク／イメージ／ボリューム の一括削除 ---
```sh
docker system prune -a --volumes
```
--- コンテナの削除 ---
```sh
docker container prune
```
--- ネットワークの削除 ---
```sh
docker network prune
```
--- ボリュームの削除 ---
```sh
docker volume prune
```
  
## 仮想マシンのIPアドレスの確認したい
```sh
docker-machine ip machine-1
```
  
## コンテナの状態を知りたい
```sh
docker inspect domain-1-managed-1 | less
```
  
## "docker-compose"でコンテナを起動したい
```sh
docker-compose up -d
```
  
## "docker-compose"で起動したコンテナを終了したい
```sh
docker-compose down
```
  
## Windows をシャットダウンできない
仮想マシンを停止してからシャットダウン。
```sh
docker-machine stop machine-1
```
  
## "oracle-db"コンテナのサーバーにログインしたい
```sh
docker-machine ssh machine-1
docker exec -it oracle-db bash
```
  
## "sqlplus"に接続したい
```txt
ユーザー:
    - system
    - PDBADMIN
パスワード: oracle
```

## curlで疎通確認したい
```shell script
curl -s -X POST \
    -H "Content-Type: application/json" \
    -d '{
          "userName":"KatoRytoa",
          "authKey":"admin",
          "payload":{
              "id":"ID-000-000",
              "name":"NAME-000-0000",
              "type":"TYPE-000-0000"
          }
        }' \
    http://192.168.99.100:50001/getAny
```
```shell script
curl -s -X POST \
    -H "Content-Type: application/json" \
    -d '{"userName":"KatoRytoa"}' \
    http://192.168.99.100:50001/getAny
```
