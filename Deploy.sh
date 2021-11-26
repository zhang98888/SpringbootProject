#!/bin/bash
#ENV
 #docker私有仓库（Harbor）
  registryUrl=8.208.82.237:5000
  registry_user="xxx"
  registry_pass="xxx"
  project_name=$1
  image_name=$2-${6:-$5}  #表示$6缺省值为$5；$6非空仅用于回滚操作)
  env=$3
  action=$4
  build_id=$5
  rollback_id=$6
  node_user=root

  if [ "${env}" == test ];then
    #测试环境
    node1=192.168.1.105
  elif [ "${env}" == master ];then
    #生产环境
    node1=192.168.1.106
  else
    echo '没有${env}环境！！！'
  fi

#Prepare
echo "project_name: $1 , image_name: $2-${6:-$5} ， env: $3 , action: $4 , build_id: $5 , rollback_id: $6"
#触发动作
case $action in
deploy | rollback)
  echo "The service is deployed in $node1"
  ssh $node_user@$node1 "docker login --username=${registry_user} --password=${registry_pass} ${registryUrl} && docker pull $image_name && docker rm -f $project_name || true &&  docker  run -itd  --name=$project_name  --restart=always -e TZ="Asia/Shanghai" --net=host  -v /home/dev/logs/$project_name:/data/logs/$project_name   $image_name && docker image prune -a  -f --filter 'until=1h'"
;;

restart)
  echo "The service is restarted in $node1"
  ssh $node_user@$node1 "docker restart ${project_name} && tailf /home/dev/logs/$project_name/${project_name}.log"
;;

*)
  echo 'Please select the correct trigger action:"deploy/rollback/restart"'
;;

esac