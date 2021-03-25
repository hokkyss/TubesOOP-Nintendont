doxygen Doxyfile
cd ./html
for i in *.dot; do
    dot -Grankdir="LR" -Tpng -o"${i%.*}.png" -Tcmapx -o"${i%.*}.map" "$i"
done
