# Docker 環境構築手順
## 前提
* Windows 10
* Docker Toolbox
    
## 仮想マシンの作成
```shell script
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

## "Oracle Database 12c Release 2"のバイナリファイルをダウンロード。
http://www.oracle.com/technetwork/database/enterprise-edition/downloads/index.html  
-> Linux x86-64

## バイナリファイルの配置
Dockerイメージ ビルド用のディレクトリに、ダウンロードしたバイナリファイルを配置する。  
パスは環境に応じて変更すること。  

```shell script
cd 'C:\Users\kator\repo'
git clone https://github.com/oracle/docker-images

cp -vip 'C:\Users\kator\Downloads\linuxx64_12201_database.zip' \
    'C:\Users\kator\repo\docker-images\OracleDatabase\SingleInstance\dockerfiles\12.2.0.1'
```

## Docker コンテナの起動
```shell script
cd 'C:\Users\kator\repo\any-rest-api\docker'
docker-compose up --build
```
## 動作確認
```shell script
curl -s -X POST \
    -H "Content-Type: application/json" \
    -d '
        {
            "userName":"KatoRytoa",
            "authKey":"admin",
            "payload":[
                {
                    "id":"ID-000-000",
                    "name":"NAME-000-0000",
                    "type":"TYPE-000-0000"
                }
            ]
        }
    ' \
    http://192.168.99.100:50000/mockCalcAny
```

## Docker コンテナの停止
```shell script
docker-compose down
```

# Tips
## コンテナ／ネットワーク／イメージ／ボリューム を全て作り直したい
```shell script
cd 'C:\Users\kator\repo\any-rest-api\docker'
docker-compose down
docker system prune -a --volumes
rm -vrf 'C:\Users\kator\repo\any-rest-api\docker\oracle-db\oradata'
docker-machine restart machine-1
eval `docker-machine env machine-1`
docker-compose up --build
```
  
## 不要なリソースを削除したい
--- 仮想マシンの削除 ---
```shell script
docker-machine rm machine-1
```
--- コンテナ／ネットワーク／イメージ／ボリューム の一括削除 ---
```shell script
docker stop `docker ps -q`
docker system prune -a --volumes
```
--- コンテナの削除 ---
```shell script
docker container prune
```
--- ネットワークの削除 ---
```shell script
docker network prune
```
--- ボリュームの削除 ---
```shell script
docker volume prune
```
  
## 仮想マシンを起動したい
```shell script
docker-machine start machine-1
```

## 仮想マシンを停止したい
```shell script
docker-machine stop machine-1
```
仮想マシンを起動したまま、Windowsのシャットダウンを行うと、 警告が表示されるが、
上記コマンドを実行し、仮想マシンを停止してから、シャットダウンを行うようにすると、 表示されなくなる。

## 仮想マシンのIPアドレスの確認したい
```shell script
docker-machine ip machine-1
```
  
## コンテナの状態を確認したい
```shell script
docker inspect any-rest-api
```
  
## "oracle-db"コンテナのサーバーにログインしたい
```shell script
docker-machine ssh machine-1
docker exec -it oracle-db bash
```
  
## sqlplus で Oracle DB に接続したい
```text
ユーザー:
    - system
    - PDBADMIN
パスワード: oracle
```
