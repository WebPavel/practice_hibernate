function createXMLHttpRequest() {
    var xhr;
    try {
        // Firefox、Safari、Chrome
        xhr = new XMLHttpRequest();
    } catch (e) {
        try {
            // IE
            xhr = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                xhr = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {

            }
        }
    }
    return xhr;
}