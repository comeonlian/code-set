# 仓库镜像

中央仓库镜像1：http://central.maven.org/maven2/

中央仓库镜像2：http://repo1.maven.org/maven2/

中央仓库镜像3：http://repo2.maven.org/maven2/

阿里云镜像：http://maven.aliyun.com/mvn/view

kettle镜像：https://nexus.pentaho.org/content/groups/omni/

Apache下载路径：http://archive.apache.org/dist/

CDH下载路径：https://repository.cloudera.com/content/repositories/releases/

# 导出git仓库的子文件夹

git init ketle

git config core.sparsecheckout true

echo "kettle/*" >> .git/info/sparse-checkout

git remote add -f origin https://192.168.100.13:3000/Developer/codes.git

git pull origin master

git https://192.168.100.13:3000/Developer/codes.git master


