<#import "../common.ftl" as c/>
<@c.page title="Car List">
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Update Car</title>
    <link rel="stylesheet" Type="text/css" href="<@spring.url '/css/style.css'/>"/>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
    <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="container" >
    <fieldset>
        <legend>Update car</legend>
        <form name="CarForm" action="" method="POST">
            <@spring.formHiddenInput "CarForm.id"/>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Brand car:</span>
                </div>
                <@spring.formInput "CarForm.brandCar" "class='form-control' readonly" "text"/>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Model car:</span>
                </div>
                <@spring.formInput "CarForm.modelCar" "class='form-control' readonly" "text"/>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Cost car:</span>
                </div>
                <@spring.formInput "CarForm.costCar" "class='form-control'" "text"/>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">License number plates:</span>
                </div>
                <@spring.formInput "CarForm.licenseNumberPlates" "class='form-control'" "text"/>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Type car:</span>
                </div>
                <@spring.formInput "CarForm.typeCar" "class='form-control' readonly" "text"/>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Car year:</span>
                </div>
                <@spring.formInput "CarForm.yearCar" "class='form-control' readonly" "text"/>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Rental price:</span>
                </div>
                <@spring.formInput "CarForm.rentalPrice" "class='form-control'" "text"/>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Repair car:</span>
                </div>
                <@spring.formSingleSelect "CarForm.repair", ListRepair, "class='form-control'"/>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Rent car:</span>
                </div>
                <@spring.formInput "CarForm.rent", "class='form-control' readonly"/>
            </div>
            <br>
            <a href="/CarRentals/car/list" Type="Button" class="btn btn-primary">Back</a>
            <input Type="submit" value="     Submit     " class="btn btn-danger"/>
        </form>
    </fieldset>
</div>
</body>
</html>
</@c.page>