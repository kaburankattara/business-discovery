# 作業ディレクトリに移動
cd ~/workspace/business-discovery/business-discovery-docker/

# cassandraのデータディレクトリを削除
rm -rf business-discovery-docker/volumes/cassandra1/datadir
rm -rf business-discovery-docker/volumes/cassandra2/datadir

# dockerを起動
docker-compose -f docker-compose.yml up --build -d
