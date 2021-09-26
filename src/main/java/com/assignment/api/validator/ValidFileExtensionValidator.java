package com.assignment.api.validator;


import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Optional;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ValidFileExtensionValidator implements ConstraintValidator<ValidFileExtension, FilePart> {

    final List<String> extensions = Lists.newArrayList("PDF", "pdf");

    @Override
    public boolean isValid(final FilePart filePart, final ConstraintValidatorContext ctx) {
        return Optional.of(filePart.filename()).filter(fp -> extensions.contains(FilenameUtils.getExtension(fp))).isPresent();
    }
}
