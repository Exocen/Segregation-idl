#!/bin/sh
if [[ $# -eq 0 ]] ; then
    echo "Veuillez indiquer un argument"
    echo "0 pour l'application Fish"
    echo "1 pour l'application Segregation"
    exit 0
fi
case "$1" in
    0) java -jar segregation.jar 0
        sh make_fish_graph.sh   ;;
    1) java -jar segregation.jar 1
        sh make_seg_graph.sh    ;;
    *) echo 'Mauvais Argument'  ;;
esac
echo "done"
exit 0
