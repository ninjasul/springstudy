(function($) {
    $.converter = {
        files : undefined,
        fileData : [],

        doDrop : function(e) {

            $("#fileList").text("");
            $.converter.fileData = [];

            var files = e.originalEvent.target.files || e.originalEvent.dataTransfer.files;

            files = Object.values(files).map(function(obj) {
                return obj;
            });

            files.sort(function ( a, b ) {
                if (a.name < b.name)
                    return -1;
                if (a.name > b.name)
                    return 1;
                return 0;
            });

            $.converter.files = files;

            for (var i = 0; i < $.converter.files.length; i++) {

                if( $.converter.files[i].size > 0 ) {
                    (function (file, i) {
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            //console.log(file);
                            $("#fileList").append("<li id='fileItem" + i + "' data-index='" + i + "' class='file-list-item'>" + file.name + "</li>");
                            $.converter.fileData[i] = e.target.result;
                        };
                        reader.readAsText(file, "UTF-8");
                    })($.converter.files[i], i);
                }
            }

            if( $.converter.files.length > 0 ) {
                $("#spanInfo").hide();
                $("#fileList").show();
            }
            else {
                $("#fileList").hide();
                $("#spanInfo").show();
            }

        },

        convert : function( fileData ) {
            var lines = fileData.split(/\r\n|\r|\n/g);

            for (var j = lines.length - 1; j >= 0; --j) {
                if( lines[j].trim() === "" ) {
                    lines.splice(j, 1);
                }
                else if (lines[j].match(/^[0-9]+$/)) {
                    lines.splice(j, 1);
                    //console.log("line matched");
                }
                else if (lines[j].match(/^[0-9][0-9]:[0-9][0-9]:[0-9][0-9]/)) {
                    lines.splice(j, 1);
                    //console.log("timestamp matched");
                }
            }

            return lines.join(" ");

        }
    }

    // 드래그 진입 이벤트
    $(document).on("dragenter", "[id=inputBox]", function(e){
        //console.log("dragenter");
        $("#inputBox").textContent = '';
        e.stopPropagation();
        e.preventDefault();
    });

    // dragover 이벤트 핸들러
    $(document).on("dragover", "[id=inputBox]", function(e){
        //console.log("dragover");
        e.stopPropagation();
        e.preventDefault();
    });

    // drop 이벤트 핸들러
    $(document).on("drop", "[id=inputBox]", function(e){
        //console.log("drop");
        //console.log(e);
        e.stopPropagation();
        e.preventDefault();
        $.converter.doDrop(e);
    });

    // drop 이벤트 핸들러
    $(document).on("click", "[id^=fileItem]", function(e){
        //console.log("click");
        e.stopPropagation();
        e.preventDefault();
        var index = $(this).data("index");
        var fileData = $.converter.fileData[index];

        if( !fileData ) {
            alert("변환할 파일이 존재하지 않습니다.");
            return false;
        }
        else {
            $("#outputBox").text($.converter.convert(fileData));
        }
    });

    $(document).on("click", "[id=btnConvert]", function(e){
        e.stopPropagation();
        e.preventDefault();

        if( !$.converter.fileData || $.converter.fileData.length <= 0 ) {
            alert("변환할 파일이 존재하지 않습니다.");
            return false;
        }
        else {

            var result = '';

            for( var i = 0, length = $.converter.fileData.length; i < length; ++i ) {
                var convertedData = $.converter.convert( $.converter.fileData[i] );
                result += convertedData + "\r\n\r\n";
            }

            $("#outputBox").text(result);
        }
    });


})(jQuery);