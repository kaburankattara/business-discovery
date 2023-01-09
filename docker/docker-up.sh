echo 作業ディレクトリに移動
cd ~/workspace/business-discovery/docker/

echo mysqlのデータディレクトリを削除
rm -rf volumes/mysql/datadir

echo cassandraのデータディレクトリを削除
rm -rf volumes/cassandra1/datadir
rm -rf volumes/cassandra2/datadir

echo dockerを起動
docker-compose -f docker-compose.yml -p business-discovery up --build -d
