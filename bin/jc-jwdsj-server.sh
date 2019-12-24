#!/bin/bash
############################################################
#          ___          _______   _____      _
#         | \ \        / /  __ \ / ____|    | |
#         | |\ \  /\  / /| |  | | (___      | |
#     _   | | \ \/  \/ / | |  | |\___ \ _   | |
#    | |__| |  \  /\  /  | |__| |____) | |__| |
#     \____/    \/  \/   |_____/|_____/ \____/
#    :: Jwdsj-Server Startup Script :: (start|stop|restart|status)
# 
#   @author YYL
############################################################

WORKING_DIR="$(pwd)"
JARFILE="jc-jwdsj-server.jar"
LOGFILE=${JARFILE%.jar}.log
XMS="128m";
XMX="1024m"

STOP_WAIT_TIME=60

get_server_pid() {
  echo `ps -ef | grep java | grep $JARFILE | awk '{print $2}'`
}

start() {
  [[ -z $server_pid ]] || { isRunning "$server_pid" && { echo "Server Already Running [$server_pid]"; return 0; } }
  echo "java -jar -Xms${XMS} -Xmx${XMX} ${WORKING_DIR}/${JARFILE} >${WORKING_DIR}/${LOGFILE} 2>&1"
  nohup java -jar -Xms${XMS} -Xmx${XMX} ${WORKING_DIR}/${JARFILE} >${WORKING_DIR}/${LOGFILE} 2>&1 &
  server_pid=`get_server_pid`
  echo "Server Started $server_pid"
}

stop() {
  [[ -n $server_pid ]] || { echo "Server Not Running!"; return 0; }
  kill -9 $server_pid
  for i in $(seq 1 $STOP_WAIT_TIME); do
    isRunning $server_pid || { echo "Server Stopped $server_pid"; return 0; }
    [[ $i -eq STOP_WAIT_TIME/2 ]] && kill $server_pid &> /dev/null
    sleep 1
  done
  echo "Unable to kill process $server_pid"
  return 1;
}

restart() {
  stop && start
}

status() {
  [[ -z "$server_pid" ]] || { isRunning "$server_pid" && { echo "Server Running [$server_pid]"; return 0; } }; echo "Server Not Running "; return 1;
}

log(){
  tail -F -n 35 $LOGFILE
}

isRunning() {
  ps -p "$1" &> /dev/null
}

action="$1"
server_pid=`get_server_pid`

case "$action" in
start)
  start "$@"; exit $?;;
stop)
  stop "$@"; exit $?;;
restart)
  restart "$@"; exit $?;;
status)
  status "$@"; exit $?;;
log)
  log "$@"; exit $?;;
*)
  echo "Usage: $0 {start|stop|restart|status}"; exit 1;
esac

exit 0