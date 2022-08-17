package motessori.school.service2;

import com.grpcdemo.Project;
import com.grpcdemo.ProjectsStudentServiceGrpc;
import com.grpcdemo.Student;
import io.grpc.stub.StreamObserver;
import montessori.school.proto.TempDB;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class ProjectsStudentServerService extends ProjectsStudentServiceGrpc.ProjectsStudentServiceImplBase {
    @Override
    public void getProjectsByStudent(Student request, StreamObserver<Project> responseObserver) {
        TempDB.getProjectsFromTempDb()
                .stream()
                .filter(project -> project.getStudentId() == request.getStudentId())
                .forEach(responseObserver::onNext);  //well get multiple projects and each the foreach will be called. It will send the projects directly to the client(streaming)not a list
        responseObserver.onCompleted();
    }
}
