package com.tupi.controllers.impl;

import com.tupi.controllers.PersonController;
import com.tupi.data.vo.v1.PersonVO;
import com.tupi.services.PersonService;
import com.tupi.services.impl.PersonServicesImpl;
import com.tupi.utils.LogUtil;
import com.tupi.utils.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
@Tag(name = "People", description = "endpoing for managing people")
public class PersonControllerImpl implements PersonController {

    @Autowired
    private PersonServicesImpl service;

    @Override
    @GetMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}
    )
    @Operation(
            summary = "find one person",
            description = "find one person",
            tags = {"People"},
            responses = {
                    @ApiResponse(
                            description = "success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            schema = @Schema(implementation = PersonVO.class)
                                    )
                            }
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public PersonVO findById(@PathVariable("id") Long id) {
      return service.findById(id);
    }

    @Override
    @GetMapping(
            value = "/findAll",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}
    )
    @Operation(
            summary = "find all people",
            description = "find all people",
            tags = {"People"},
            responses = {
                    @ApiResponse(
                            description = "success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
                                    )
                            }
                    ),
                    @ApiResponse(description = "No Content", responseCode = "2044", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public List<PersonVO> findAll() {
        return service.findAll();
    }

    @Override
    @PostMapping(
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}
    )
    @Operation(
            summary = "Create one person",
            description = "Create one person",
            tags = {"People"},
            responses = {
                    @ApiResponse(
                            description = "success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            schema = @Schema(implementation = PersonVO.class)
                                    )
                            }
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public PersonVO create(@RequestBody PersonVO PersonVO) {
        LogUtil<PersonVO> logUtil = new LogUtil<>();
        logUtil.logTudo(PersonVO);
        PersonVO.setKey(null);

        return service.create(PersonVO);
    }

    @Override
    @PutMapping(
            value = "{id}",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}
    )
    @Operation(
            summary = "Update one person",
            description = "Update one person",
            tags = {"People"},
            responses = {
                    @ApiResponse(
                            description = "Update",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            schema = @Schema(implementation = PersonVO.class)
                                    )
                            }
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public PersonVO update(@PathVariable("id") Long id, @RequestBody PersonVO PersonVO) {
        return service.update(id, PersonVO);
    }

    @Override
    @DeleteMapping("{id}")
    @Operation(
            summary = "Delete one person",
            description = "Delete one person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        return service.deleteById(id);
    }
}
