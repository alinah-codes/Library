<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <section class="text-center text-lg-start">
        <div class="card m-3">
            <div class="row g-0 d-flex align-items-center">
                <div class="col-lg-4 d-none d-lg-flex">
                    <img src="https://mdbootstrap.com/img/new/ecommerce/vertical/004.jpg"
                         alt="Login Image"
                         class="w-100 rounded-t-5 rounded-tr-lg-0 rounded-bl-lg-5"/>
                </div>
                <div class="col-lg-8">
                    <div class="card-body py-5 px-md-5">

                        <form id="login-form" action="/login" method="post">
                            <#if _csrf??>
                                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                       value="${(_csrf.token)!'csrf-token'}"/>
                            </#if>

                            <!-- Email input -->
                            <div class="form-outline mb-4">
                                <input type="email" id="form2Example1" class="form-control" name="username" required/>
                                <label class="form-label" for="form2Example1">
                                    Mail
                                </label>
                            </div>

                            <!-- Password input -->
                            <div class="form-outline mb-4">
                                <input type="password" id="form2Example2" class="form-control" name="password" required/>
                                <label class="form-label" for="form2Example2">
                                    Password
                                </label>
                            </div>

                            <!-- Submit button -->
                            <button type="submit"
                                    class="btn btn-primary btn-block mb-4">
                                Login
                            </button>

                            <div>
                                <a href="/auth/forgot-password">
                                    Forgot your password?
                                </a>
                            </div>
                        </form>
                        <#if errorMessage??>
                            Wrong username or password
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </section>
</@main.layout>
