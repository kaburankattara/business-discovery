if [ ! -e '/check' ]; then
    touch /check
    # 初回起動時に実行させたいコマンドをここに書く
    echo "セットアップ"

    sleep 100

    echo setup cassandra

    cqlsh cassandra1 -f /tmp/setup.cql

    echo "セットアップ完了"
else
    # 2回目以降
    echo "セットアップ済"
fi
