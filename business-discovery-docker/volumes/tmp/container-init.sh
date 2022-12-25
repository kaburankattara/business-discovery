if [ ! -e '/check' ]; then
    touch /check
    # 初回起動時に実行させたいコマンドをここに書く
    echo "セットアップ"

    # javaをインストール
    mkdir ~/java
    tar xvf /opt/openlogic-openjdk-jre-8u352-b08-linux-x64.tar.gz -C ~/java --strip-components 1
    mv ~/java /usr/local/java

    # Google Chrome
    cp /opt/google.chrome.repo /etc/yum.repos.d/google.chrome.repo
    yum -y install google-chrome-stable-107.0.5304.121-1.x86_64

    echo "セットアップ完了"
else
    # 2回目以降
    echo "セットアップ済"
fi
