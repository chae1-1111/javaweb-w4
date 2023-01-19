/*!
* Start Bootstrap - Simple Sidebar v6.0.5 (https://startbootstrap.com/template/simple-sidebar)
* Copyright 2013-2022 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-simple-sidebar/blob/master/LICENSE)
*/
// 
// Scripts
// 

window.addEventListener('DOMContentLoaded', event => {

    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
        //     document.body.classList.toggle('sb-sidenav-toggled');
        // }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

});

const getErrorMsg = (code) => {
    if(code == "NotEmpty") {
        return "을(를) 입력해주세요.";
    }else if(code == "Size") {
        return "은(는)3자 이상, 100자 이하로만 입력해주세요.";
    }
};

const vaildCheck = (errors) => {
    for (let i = 0; i < errors.length; i++) {
        let field = errors[i].field;
        let input = document.querySelector(`[name=${field}]`);
        let feedback = document.querySelector(`#${field}Validation`);
        feedback.innerHTML += "<p style='margin: 0;'>" + field + getErrorMsg(errors[i].code) + "</p>";
        input.classList.add("is-invalid");
        input.addEventListener("click", e => {
            e.target.classList.remove("is-invalid");
            feedback.innerHTML = "";
        }, {once: true});
    }
}


// if Has Valid Error, set default value for board form
const setDefaultValue = ({title, content, writer, fileNames}, showUploadFile) => {
    if(title && title.length > 0) document.querySelector("[name=title]").value = title;
    if(content && content.length > 0) document.querySelector("[name=content]").value = content;
    if(writer && writer.length > 0) document.querySelector("[name=writer]").value = writer;

    fileNames.forEach(fileName => {
        let arr = fileName.split("_");
        showUploadFile({uuid: arr[1], fileName: arr[2], link: fileName.split("/view/")[1]});
    })
}