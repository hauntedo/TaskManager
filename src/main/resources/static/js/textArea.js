
function todo(x) {
    var an = x.value;
    var ln = an.length;
    // полагаем, что на символ уходит 8 пикселей по ширине, а в обычную строку влезает скажем 25 символов
    // (потом подгонишь точнее)
    if (ln > 25) {
        var neww = ln * 8;
        x.style.width = neww + 'px';
    }
}
