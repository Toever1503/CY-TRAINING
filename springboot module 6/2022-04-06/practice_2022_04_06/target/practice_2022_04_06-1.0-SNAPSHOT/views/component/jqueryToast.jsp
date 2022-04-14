<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="toasts">
    <script>
        jQuery(($) => {
            "use strict";

            var defaultConfig = {
                type: '',
                autoDismiss: false,
                container: '#toasts',
                autoDismissDelay: 2500,
                transitionDuration: 500
            };

            $.toast = function (config) {
                var size = arguments.length;
                var isString = typeof (config) === 'string';

                if (isString && size === 1) {
                    config = {
                        message: config
                    };
                }

                if (isString && size === 2) {
                    config = {
                        message: arguments[1],
                        type: arguments[0]
                    };
                }

                return new toast(config);
            };

            var toast = function (config) {
                config = $.extend({}, defaultConfig, config);
                // show "x" or not
                var close = config.autoDismiss ? '' : '&times;';

                // toast template
                var toast = $([
                    '<div class="toast ' + config.type + '">',
                    '<p>' + config.message + '</p>',
                    '<div class="close">' + close + '</div>',
                    '</div>'
                ].join(''));

                // handle dismiss
                toast.find('.close').on('click', function () {
                    var toast = $(this).parent();

                    toast.addClass('hide');

                    setTimeout(function () {
                        toast.remove();
                    }, config.transitionDuration);
                });

                // append toast to toasts container
                $(config.container).append(toast);

                // transition in
                setTimeout(function () {
                    toast.addClass('show');
                }, config.transitionDuration);

                // if auto-dismiss, start counting
                if (config.autoDismiss) {
                    setTimeout(function () {
                        toast.find('.close').click();
                    }, config.autoDismissDelay);
                }

                return this;
            };
        });
    </script>
    <style>
        #toasts {
            min-height: 0;
            position: fixed;
            right: 20px;
            top: 40px;
            width: 400px;
            z-index: 999;
        }

        #toasts p {
            color: white;
            font-size: 20px;
        }

        #toasts .toast {
            background: #d6d8d9;
            border-radius: 3px;
            box-shadow: 2px 2px 3px rgba(0, 0, 0, .1);
            color: rgba(0, 0, 0, .6);
            cursor: default;
            margin-bottom: 20px;
            opacity: 0;
            position: relative;
            padding: 20px;
            transform: translateY(15%);
            transition: opacity .5s ease-in-out, transform .5s ease-in-out;
            width: 100%;
            will-change: opacity, transform;
            z-index: 1100;
        }

        #toasts .toast.success {
            background: #2dc511;
        }

        #toasts .toast.warning {
            background: #ffa533;
        }

        #toasts .toast.info {
            background: #2cbcff;
        }

        #toasts .toast.error {
            background: #f44336;
        }

        #toasts .toast.show {
            opacity: 1;
            transform: translateY(0);
            transition: opacity .5s ease-in-out, transform .5s ease-in-out;
        }

        #toasts .toast.hide {
            height: 0;
            margin: 0;
            opacity: 0;
            overflow: hidden;
            padding: 0 30px;
            transition: all .5s ease-in-out;
        }

        #toasts .toast .close {
            cursor: pointer;
            font-size: 24px;
            height: 16px;
            margin-top: -10px;
            position: absolute;
            right: 14px;
            top: 50%;
            width: 16px;
        }
    </style>

    <c:if test="${toastType == 'success'}">
        <script>
            $(document).ready(function () {
                jQuery.toast({
                    type: 'success',
                    message: '${message}',
                    autoDismiss: true
                });
            });
        </script>
    </c:if>
    <c:if test="${toastType == 'error'}">
        <script>
            $(document).ready(function () {
                jQuery.toast({
                    type: 'error',
                    message: '${message}',
                    autoDismiss: true
                });
            });
        </script>
    </c:if>
</div>