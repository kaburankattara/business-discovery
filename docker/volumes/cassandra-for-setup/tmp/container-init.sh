if [ ! -e '/check' ]; then
    touch /check
    # 初回起動時に実行させたいコマンドをここに書く
    echo "セットアップ"

    isLoop="true"
    while [ $isLoop = "true" ];
    do
      sleep 10

      cqlsh cassandra1 -e "describe keyspaces;"
      if [ $? -eq 0 ]; then
        echo "cassandraに接続できるようになりました。"
        isLoop=false
      else
        echo "まだcassandraに接続出来ません。"
      fi
    done

    echo setup cassandra
    cqlsh cassandra1 -f /tmp/setup.cql

    echo "セットアップ完了"
else
    # 2回目以降
    echo "セットアップ済"
fi
