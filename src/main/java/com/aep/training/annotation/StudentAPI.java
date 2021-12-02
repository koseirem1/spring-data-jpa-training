package com.aep.training.annotation;

import com.aep.training.util.ApiConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.lang.annotation.*;

@Target(ElementType.TYPE) //element type sınıf üsünde tanımlanabilir sadece.
@Retention(RetentionPolicy.RUNTIME)
@RestController
@RequestMapping(ApiConstant.ROOT+"/students")
public @interface StudentAPI {
}
