<script>
    $(function() {
        $(".needs-validation").validate({
            rules: {
                onfocusout: false,
                onkeyup: false,
                onclick: false,
                name: {
                    required : true
                },
                phone: {
                    required: true
                },
                email: {
                    required: true,
                    email: true
                },

            },
            messages: {
                name: {
                    required: "Please enter your name"
                },
                phone:{
                    required: "Please provide a phone",
                    minlength: "Your phone number  must be at least 10 or 11 number"
                },
                email: {
                    required: "Please provide a email",
                    email: "Please enter a valid email address"
                },

            },
            submitHandler: function(form) {
                form.submit();
            }
        });
    });
</script>