-- キースペースを作成
DROP KEYSPACE IF EXISTS business_discovery;
CREATE KEYSPACE business_discovery
 WITH REPLICATION = {
  'class' : 'NetworkTopologyStrategy',
  'datacenter1' : 1
 } ;

-- 作成したキースペースを選択
use business_discovery;

-- カラムファミリを作成
DROP COLUMNFAMILY IF EXISTS video;
CREATE COLUMNFAMILY video
(
  Id VARCHAR PRIMARY KEY
  ,Title VARCHAR
  ,ChannelId VARCHAR
  ,ChannelTitle VARCHAR
  ,DefaultAudioLanguage VARCHAR
  ,PublishedAt VARCHAR
  ,CommentCount varint
  ,FavoriteCount varint
  ,LikeCount varint
  ,DislikeCount varint
  ,ViewCount varint
);
